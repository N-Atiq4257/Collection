#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

//	Nafil Atiq!!!! Yeah that's me!! It's me it's me it's me it's me
//	This is a linked-queue implementation of a thread pool.
//	I made it so that I can learn structs a little bit more.
//	Also since I've been really rusty with pointers since I last used
// 	C++

struct PoolWorker {
	pthread_t thread; // The thread that will be run for execution.
	struct PoolWorker* next; // a pointer to the next one.
};

// We'll be making a queue of workers, that'll be our pool here...
// We will have one elemtn, a head which points to pool workers.
// Those workers then can do STUFF!!! :D
struct Pool {
	struct PoolWorker *head;
	// this case will just use a head alone for ease's sake
	// although usually it's far more efficient to have a tail pointer.
};

// a function that exists just to clear any worker.
// a roundabout solution, but it kinda... (kinda? works)


void *clearWorker(struct PoolWorker *worker){
	pthread_t *thisThread = &worker->thread;
	//printf(thisThread);
	pthread_join(* thisThread, NULL);

	free(worker);
	//return 0;
}


void *testRun(){
	// do... something I guess.
	int x = 0;
	for (int i = 0; i<70000000; i++){
		x++; // really silly but it works as a wait
	}
	printf("Hello, World!\n");

	pthread_exit(NULL);
	//return 0;
}

// this function adds a new pool worker to the heap
// no that's literally it.. :(
void PoolEnqueue(struct Pool *p){

	// get the worker that we're pointing to.
	// initial case.
	struct PoolWorker* n = p->head;
	
	// NULL means we need to allocate.
	if (n == NULL){
		// allocate you on the heap!!!!
		struct PoolWorker *newHead = (struct PoolWorker*) malloc(sizeof(struct PoolWorker)); 
		p->head = newHead;
	}else{
		// now we'll go to the tail.
		while(n != NULL){
			// if the next one is null, we're at the head.
			// so we'll juts allocate now.
			if (n->next == NULL){
				// we're at the end, allocate a new one.
				struct PoolWorker *tail = (struct PoolWorker*) malloc(sizeof(struct PoolWorker));
				n->next = tail;
				// now we'll leave.
				break;
			}

			// now proceed
			n = n->next;
		}
	}
	printf("A new thread has been added to the pool!\n");
}

// We'll get it to run something, and then DIE.
void PoolExecute(struct Pool *p){

	struct PoolWorker *pop = p->head;

	// if the pool is empty.
	if (p->head == NULL){
		printf("The queue is empty :(\n");
		return;
	}

	p->head = pop->next; // now this one is out of the queue.

	printf("Attempting to run this thread now :o\n");
	pthread_t *thisThread = &pop->thread;
	pthread_t rm;

	// execute this worker.
	pthread_create(&thisThread, NULL, testRun, NULL);

	// now make another one run to wait and free the worker itself.
	pthread_create(&rm, NULL, clearWorker, pop);
}

int main(int argc, char *argv[]){
	printf("Testing a pool implemented using a queue!\n");
	printf("This does NOT use signals, rather, it pops and pushes new items.\n");
	struct Pool *threadPool = NULL;

	// let's declare 10 workers.
	for (int i = 0; i<10; i++){
		PoolEnqueue(&threadPool); // new worker i hope
	}

	printf("> Doing simultaneous requests <\n");
	for (int i = 0; i<3; i++){
		PoolExecute(&threadPool);
	}
	printf("> Doing delayed requests<\n");
	for (int i = 0; i<5; i++){
		PoolExecute(&threadPool); // do it!!!
		sleep(3);
	}
	// let's do another one :)
	while (1){}

	return 0;
}
