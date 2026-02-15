def compare_string(string1,string2):
    len1 = len(string1)
    len2 = len(string2)
#    print("\nLength of String 1: ", len1, "\nLength of String 2: ", len2)
    min_len = min(len1, len2)
    i=0
    result_text = ""
    while i < min_len:
        if string1[i] ==  string2[i]:
            result_text = result_text + string1[i]
            i = i + 1
        else:
            result_text = result_text + "-"
            i = i + 1
    diff = max(len1, len2) - min_len
    i=0
    while i < diff:
        result_text = result_text + "-"
        i = i + 1
    print("The result String: ", result_text)


def reverse_string(string1):
    rev = ""
    for c in string1:
        rev = c + rev
    print("Reversed String: ", rev)



def main():
    string1 = input("Enter String 1 :")
    string2 = input("Enter String 2 :")
    compare_string(string1,string2)
    reverse_string(string1)


if __name__ == "__main__":
    main()