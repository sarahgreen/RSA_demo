/**********************************************
 * readMe.txt for RSA project
 * @author SarahGreen
 *********************************************/

HOW TO RUN

The user should be in the directory containing both my "message"
file and the "rsa" folder containing my "Makekeys.java" and
"Encode.java" files.

To compile all my java classes, execute:
$ javac rsa/*.java

To make the keys, call rsa/Makekeys and specify a name for
the public and private key files.
$ java rsa/Makekeys bob

To encrypt the "message" file to a new file called "code,"
call rsa/Encode and specify the public key.
$ java rsa/Encode bob.public < message > code

To decrypt the "code" file and display the original message,
call rsa/Encode and specify the private key.
$ java rsa/Encode bob.private < code

To encrypt "message" and pipe it immediately to decrypt, execute:
$ java rsa/Encode bob.public < message | java rsa/Encode bob.private

All files can be checked in UNIX using a simple text editor, e.g. pico.

-----

METHOD

The "message" file should be a finite list of integers, separated
by line breaks. The integers should not exceed 4096.

In my Makekeys class I instantiate a Makekeys object, get its name
(e.g. "bob") from the command line argument, and run the RSA method
on it to change its instance variables e, d, and N to numbers
appropriate for RSA encryption. Then the files containing the public and
private keys are generated, using the appropriate name and containing
the correct variables for encryption or decryption.

In my Encode class I read in a message file from System.in, and parse an
integer array from the file. Then I parse the file specified in the command
line (public or private key) to locate the exponent and modulus values.
Then I exponentiate/modulate each integer in the message array accordingly,
and print the changed values.