## Vigenère cipher

- method of encrypting alphabetic text where each letter of the plaintext is encoded with a different Caesar cipher 
- increment is determined by the corresponding letter of another text = key

### Example

if the plaintext is "attacking tonight" and the key is OCULORHINOLARINGOLOGY, then

- the first letter a of the plaintext is shifted by 14 positions in the alphabet (O of the key is the 14th letter of the alphabet, counting from 0), yielding o;
- the second letter t is shifted by 2 (C of the key means 2) yielding v;
- the third letter t is shifted by 20 (U) yielding n, with wrap-around;
- and so on 
- yielding the message ovnlqbpvt eoeqtnh 

If the recipient of the message knows the key, they can recover the plaintext by reversing this process.

The Vigenère cipher is therefore a special case of a polyalphabetic substitution.
