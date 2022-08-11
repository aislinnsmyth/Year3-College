import socket
import threading
from simplecrypt import encrypt, decrypt

HOST = "127.0.0.1"  # Standard loopback interface address (localhost)
PORT = 65432     # Port to listen on (non-privileged ports are > 1023)

def create_socket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 0) #Re-use the socket
    s.bind((HOST, PORT))
    s.listen(10)

    while True:
        conn, addr = s.accept()
        print ('Got connection from', addr)
        threadListener = threading.Thread(target = getData(s,conn), args=(conn,addr)) #Ready to create a new thread for any request made and run requestReceived
        threadListener.daemon = True
        threadListener.start()  

def getData(s,conn):
    data = conn.recv(1024)
    print(f"Received {data!r}")
    conn.sendall(data)

if __name__ == '__main__':
    create_socket()