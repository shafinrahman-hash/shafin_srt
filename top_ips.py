from collections import Counter

def top_ip(access_log):
    ip = []
    with open (access_log, "r") as f:
        for line in f:
            ips = line.split()[0]
            ip.append(ips)
        ip_counts = Counter(ip)
        print(ip)
        print(ip_counts)
        for ip, count in ip_counts.most_common():
            print(ip,":",count)


top_ip("access.log")