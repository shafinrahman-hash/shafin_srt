def print_string(scan_string):
    print ("The entered string :", scan_string)

while (True):
    scn_string = input ("Enter the string :")
    scn_string = scn_string.lower()
    if (scn_string == "exit"):
        print("Exiting this loop")
        break
    else:
       print_string(scn_string)
