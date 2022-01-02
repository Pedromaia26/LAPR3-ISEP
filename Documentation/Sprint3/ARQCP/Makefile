us313: us313.o us315.o
	gcc us313.o us315.o -o us313
us313.o: main.c us315.h
	gcc -Wall -g -c main.c -o us313.o
us315.o: us315.s
	gcc -Wall -g -c us315.s -o us315.o
run: us313
	./us313
clean:
	rm ∗ . o us313
