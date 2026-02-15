import subprocess

def command_execute():
    arg1="-l"
    result=subprocess.run(["ls", f"{arg1}"], capture_output=True, text=True)
    print(result.stdout)


def main():
    command_execute()

if __name__ == "__main__":
    main()