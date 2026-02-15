import argparse
import sys
import json
from datetime import datetime

parser = argparse.ArgumentParser()
parser.add_argument('--file')
parser.add_argument('--summary', action='store_true')
parser.add_argument('--test')
parser.add_argument('--agent')
parser.add_argument('--status')
parser.add_argument('--p95')
parser.add_argument('--date')
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
    for line in read_line(path):
        log=json.loads(line)
        agent=log.get("agent")
        test=log.get("test")
        p95_ms=log.get("p95_ms")
        status=log.get("status")
        time=log.get("ts")
        if args.date:
            target_time=datetime.strptime(args.date, "%Y-%m-%d").date()
            logtime=datetime.strptime(time, "%Y-%m-%dT%H:%M:%SZ").date()
        if args.test and test!=args.test:
            continue
        if args.agent and agent!=args.agent:
            continue
        if args.status and status!=args.status:
            continue
        if args.p95 and int(p95_ms) < int(args.p95):
            continue
        if target_time != logtime:
            continue
        else:
            print(log) 


if __name__ == "__main__":
    main()