import shutil
def disk_usage(path,threshold):
    total,used,free = shutil.disk_usage(path)
    usage_percentage = (used/total) * 100
    
    if (usage_percentage > threshold):
        print("ERROR: Disk usage crossed 80%")
    else:
        print("OK: Disk usage is with in the limit")

disk_usage("/",80)