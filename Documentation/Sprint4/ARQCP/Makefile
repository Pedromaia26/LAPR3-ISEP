sprint4: us410.o main.o
	gcc us410.o main.o -o sprint4

us410.o: us410.s us410.h
	gcc -g -Wall -c us410.s -o us410.o
	
main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
clean:
	rm -f *.o sprint4
	
run: sprint4
	./sprint4
