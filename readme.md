#Threads
	 Simulates a simple banking system with multiple threads: customers arrive at random intervals and wait in line to be served by a teller. Tellers serve customers one at a time and are able to handle multiple customers simultaneously.
	 One for customers and one for tellers. The customer thread simulates customers arriving at the bank by adding a customer ID to the customer queue at random intervals.
	 The teller thread simulates tellers serving customers by removing a customer ID from the queue and printing that the customer is being served.
	 Use a Queue data structure to store the customer IDs and a lock object to synchronize access to the queue.