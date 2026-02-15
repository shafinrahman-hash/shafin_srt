import argparse
import json

parser = argparse.ArgumentParser()
parser.add_argument("--logfile")
args = parser.parse_args()
print(args)

def read_file(logfile):
    with open (logfile,'r') as file:
        for line in file:
            log = json.loads(line.strip())
            print(log.get("ts"))



def main():
    log_file = args.logfile
    read_file(log_file)

if __name__ == "__main__":
    main()