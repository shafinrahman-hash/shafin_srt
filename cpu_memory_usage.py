import psutil

cpu = psutil.cpu_percent(interval=1)
mem = psutil.virtual_memory().percent

if cpu > 80:
    print(f"⚠️ CPU high: {cpu}%")

if mem > 80:
    print(f"⚠️ Memory high: {mem}%")

print(f"CPU: {cpu}%, Memory: {mem}%")