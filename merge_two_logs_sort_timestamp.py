import argparse
import json
from datetime import datetime

parser = argparse.ArgumentParser()
parser.add_argument("--filename1")
parser.add_argument("--filename2")

args = parser.parse_args()

def read_file(file):
    if file:
        with open(file, 'r') as f:
            for line in f:
                yield line.strip()

def merge_logs(log_file1, log_file2):
    list_log1 = []
    list_log2 = []
    for line in read_file(log_file1):
        interm_log = line.rstrip(",").strip('"').split()
        list_log1.append({"ts" : interm_log[0] +" "+ interm_log[1], "Event" : interm_log[2], "Task" : interm_log[3]})
    for line in read_file(log_file2):
        interm_log = line.rstrip(",").strip('"').split()
        list_log2.append({"ts" : interm_log[0] +" "+ interm_log[1], "Event" : interm_log[2], "Task" : interm_log[3]})
    log_final = list_log1 + list_log2
    return log_final

def log_sort(log):
    return datetime.strptime(log["ts"],"%Y-%m-%d %H:%M:%S")

def sort_log(log):
    logs_sorted=sorted(log, key=log_sort)
    return logs_sorted

def main():
    if not args.filename1 or not args.filename2:
        print("Missing arguments.....!")
        return
    
    log_file1 = args.filename1
    log_file2 = args.filename2
    log_final = merge_logs(log_file1, log_file2)
    sorted_logs = sort_log(log_final)
    with open("log_aggregate.txt",'w') as f:
        json.dump(sorted_logs,f,indent=3)


    
if __name__ == "__main__":
    main()