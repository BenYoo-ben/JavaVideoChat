package main

import (
	"encoding/binary"
	"io"
	"fmt"
	"net"
	"os"
	//"time"
)

const (
	CONN_HOST = "192.168.56.1"
	CONN_PORT = "55554"
	CONN_TYPE = "tcp"

	BUFF_SIZE = 8192
)

func main() {
	//Listen for incoming connections.
	l, err := net.Listen(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		fmt.Println("Error listening:", err.Error())
		os.Exit(1)
	}

	//Close the listener when the application closes.
	defer l.Close()

	fmt.Println("Listening on " + CONN_HOST + ":" + CONN_PORT)
	for {

		//Listening for an incoming connections
		conn, err := l.Accept()
		if err != nil {
			fmt.Println("Error accepting ", err.Error())
			os.Exit(1)
		}

		//Handle connections in a new goroutine
		go handleRequest(conn)
	}
}

//Handles incoming requests.
func handleRequest(conn net.Conn) {
	var err error
	prefix := make([]byte, 4)
	
	_, err = io.ReadFull(conn, prefix)
	if err != nil{
		fmt.Printf("prefix read err\n")
	}
	length := binary.BigEndian.Uint32(prefix)
	
	msg := make([]byte, int(length))
	
	
	
	_, err = io.ReadFull(conn, msg)
	if err != nil{
		fmt.Printf("msg read err\n")
	}
	
	
	fmt.Printf("[%d]Read:\n%s\n",length,string(msg))
	
	//time.Sleep(time.Second * 5)
	conn.Write([]byte("Hello Client"))
	
	
	
}
