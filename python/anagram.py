def is_anagram(str1,str2):
    print(str1[0])
    str1 = sorted(str1)
    str2 = sorted(str2)
    if str1 == str2:
        print("The strings are anagrams")
    else:
        print("The strings are not anagram")

string1 = input("Enter the string1: ")
string2 = input("Enter the string2: ")
is_anagram(string1,string2)