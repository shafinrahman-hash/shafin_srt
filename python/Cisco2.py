import argparse
import sys
import json
from datetime import datetime
from collections import Counter

parser = argparse.ArgumentParser()
parser.add_argument('--file')
parser.add_argument('--summary', action='store_true')
parser.add_argument('--status')
parser.add_argument('--groupby')
args = parser.parse_args()

def read_line(path):
    if path:
        with open(path) as f:
            for line in f:
                yield line.strip()
    else:
        for line in sys.stdin:
            yield line.strip()

def main():
    path=args.file
    issue_by_group = Counter()
    for line in read_line(path):
        log=json.loads(line)
        status=log.get("status")
        key=log.get(args.groupby)
        if status == "OK":
            continue
        else:
            print(log)
            issue_by_group[key] = issue_by_group[key] + 1
            for key, count in issue_by_group.items():
                print(f"{key}:{count}")
#            print(issue_by_group.most_common())

if __name__ == "__main__":
    main()