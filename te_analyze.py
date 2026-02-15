#!/usr/bin/env python3
import argparse
import json
import sys
from collections import Counter

def read_lines(path):
    if path:
        with open(path, "r") as f:
            for line in f:
                yield line.strip()
    else:
        for line in sys.stdin:
            yield line.strip()

def main():
    parser = argparse.ArgumentParser(description="Simple ThousandEyes log analyzer")
    parser.add_argument("--file", help="Log file (JSONL). If not given, read stdin")
    parser.add_argument("--test", help="Filter by test name")
    parser.add_argument("--agent", help="Filter by agent name")
    parser.add_argument("--p95", type=int, help="Show only p95_ms >= value")
    parser.add_argument("--summary", action="store_true", help="Print summary only")
    args = parser.parse_args()

    total = 0
    non_ok = 0
    tests = Counter()
    agents = Counter()

    try:
        for line in read_lines(args.file):
            if not line:
                continue

            log = json.loads(line)

            test = log.get("test")
            agent = log.get("agent")
            p95 = int(log.get("p95_ms", 0))
            status = log.get("status")

            # Apply filters
            if args.test and test != args.test:
                continue
            if args.agent and agent != args.agent:
                continue
            if args.p95 and p95 < args.p95:
                continue

            total += 1
            tests[test] += 1
            agents[agent] += 1
            if status != "OK":
                non_ok += 1

            if not args.summary:
                print(f'{log["ts"]} agent={agent} test="{test}" p95={p95} status={status}')

        if args.summary:
            print("Matched records:", total)
            print("Non-OK records:", non_ok)
            print("Top tests:", tests.most_common(3))
            print("Top agents:", agents.most_common(3))

    except Exception as e:
        print("Error:", e, file=sys.stderr)
        sys.exit(2)

if __name__ == "__main__":
    main()
