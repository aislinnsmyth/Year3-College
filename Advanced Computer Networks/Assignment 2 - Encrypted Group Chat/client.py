import socket
from simplecrypt import encrypt, decrypt
#import threading

HOST = "127.0.0.1"  # The server's hostname or IP address
PORT = 65432  # The port used by the server



def inputs():
    username = input("Enter your login for the application. followed by a: ")
    choice = int(input("Press 1 to login to the group chat, \n2 to add a member to the group, \n3 to delete a member, \n4 to terminate. \n"))
    if(choice == 1):
        print("Checking if you are apart of the groupchat...")
        lines = open("chatMembers.txt").read().splitlines()
        for line in lines:
            if(str(username) in lines):
                msg = input("Enter the message you would like to send")
                chat(username,msg)
            else:
                encryptedChat()
    if(choice == 2):
        print("Checking if you are apart of the groupchat...")
        lines = open("chatMembers.txt").read().splitlines()
        for line in lines:
            if(str(username) in lines):
                username = input("Please enter the username of the person you would like to add, followed by a : ")
                add(username)
            else:
                print("Access denied, you are not a member therefore cannot add members to the groupchat.")
                inputs()
    if(choice == 3):
        print("Checking if you are apart of the groupchat...")
        lines = open("chatMembers.txt").read().splitlines()
        for line in lines:
            if(str(username) in lines):
                username = input("Please enter the username of the individual you would like to remove, followed by a : ")
                remove(username)
            else:
                print("Access denied, you are not a member therefore cannot remove members to the groupchat.")
                inputs()
    if(choice == 4):
        exit

def chat(username,msg):
        s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        byteMsg = bytes(msg,"utf-8")
        s.send(encrypt(username, byteMsg))
        data = s.recv(1024)
        decryptedData = decrypt(username,data)
        byteUsername = bytes(username,"utf-8")
        print(byteUsername + decryptedData)
            
def encryptedChat():
        s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        print("You are not apart of the group chat, therefore every message will be encrypted")
        data = s.recv(1024)
        print(f"Received {data!r}")
        print(data)
#        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 0) #Re-use the socket
#        s.bind((HOST, PORT))
#        s.listen(10)
#        conn, addr = s.accept()
#        threadListener = threading.Thread(target = getData(s,conn), args=(conn,addr)) #Ready to create a new thread for any request made and run requestReceived
#        threadListener.daemon = True
#        threadListener.start() 
    

def add(username):
    lines = [username]
    with open('chatMembers.txt', 'a') as f:
        for line in lines:
            f.write(line)
            f.write('\n')
    inputs()
    


def remove(username):
    with open('chatMembers.txt') as fin, open('wordsCleaned.txt', 'wt') as fout:
        list(fout.write(line) for line in fin if line.rstrip() != username)
        inputs()

     


if __name__ == '__main__':
    inputs()