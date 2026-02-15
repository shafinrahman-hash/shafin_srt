import argparse
import json
from datetime import datetime
from collections import Counter

parser = argparse.ArgumentParser()
parser.add_argument('--filename')
parser.add_argument('--status')
args = parser.parse_args()

def read_file(path):
    with open(path, 'r') as f:
        for lines in f:
            yield lines.strip()

def sort_log(target_log):
    return datetime.strptime(target_log["ts"], "%Y-%m-%dT%H:%M:%SZ")

def find_max_gap(sorted_logs):
    Max_gap = None
    Final_curr = None
    Final_prev = None
    for prev,curr in zip(sorted_logs, sorted_logs[1:]):
        t1 = datetime.strptime(prev["ts"],"%Y-%m-%dT%H:%M:%SZ")
        t2 = datetime.strptime(curr["ts"],"%Y-%m-%dT%H:%M:%SZ")
        print("\nPrevious log :", t1, "Current Log :", t2)
        gap = t2 - t1
        if Max_gap is None:
            Max_gap = gap
            Final_curr = curr
            Final_prev = prev

        elif gap > Max_gap:
            Max_gap = gap
            Final_curr = curr
            Final_prev = prev
    print("Max Gap : ", gap)
    print("Current : ", Final_curr)
    print("Previous :", Final_prev)

def main():
    path = args.filename
    target_log = []
    for line in read_file(path):
        log = json.loads(line)
        status = log.get("status")
        time = datetime.strptime(log.get("ts"), "%Y-%m-%dT%H:%M:%SZ")
        target_log.append(log)
    sorted_logs = sorted(target_log, key=sort_log)
    find_max_gap(sorted_logs)

if __name__ == "__main__":
    main()