def len_string(str1, str2):
    cnt1 = len (str1)
    cnt2 = len (str2)
    print("Length of string1 : ", cnt1)
    print("Length of string2 : ", cnt2)

def compare_string(str1, str2):
    count = min(len(str1), len(str2))
    i=0
    txt=""
    while (i < count):
        if (str1[i] == str2[i]):
            txt += str1[i]
        else:
            txt += "-"
        i += 1
    diff = max(len(str1), len(str2)) - count
    i=0
    while (i<diff):
        txt += "-"
        i += 1
    print("Here is the text :", txt)


string1 = input("Enter the string1: ")
string2 = input("Enter the string2: ")
len_string(string1,string2)
compare_string(string1,string2)