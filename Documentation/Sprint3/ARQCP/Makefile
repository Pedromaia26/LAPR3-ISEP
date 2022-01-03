projeto: us314.o us315.o main.o
	gcc us314.o us315.o main.o -o projeto
	
us314.o: us314.s us314.h
	gcc -g -Wall -c us314.s -o us314.o
	
us315.o: us315.s us315.h
	gcc -g -Wall -c us315.s -o us315.o
	
main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
clean:
	rm -f ∗.o projeto
	
run: projeto
	./projeto
	

