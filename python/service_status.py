import os
import time

SERVICE = "nginx"

while True:
    status = os.system(f"systemctl is-active --quiet {SERVICE}")
    
    if status != 0:  # non-zero means service is not running
        print(SERVICE, " is down! Restarting...")
        os.system(f"sudo systemctl restart {SERVICE}")
    else:
        print(SERVICE, " is running fine.")
    
    time.sleep(30)