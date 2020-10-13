run:Main.class
	java Playfair $(ARGS)

Main.class: Playfair.java
	javac Playfair.java

clean:
	rm Playfair.class
