/*String Tokenization and Regex (Lecture 4) 2017-05-15

StringTokenizer Class
--------
	StringTokenizer is used to recover the words (aka tokens) in a multi-word string)
	You can use whitespace characters to separate tokens (or change it to anything you want)
		These are called delimiters
	In order to use StringTokenizer class, you must import it first 
		*/ import java.util.StringTokenizer; /*
	The constructor of StringTokenizer (two ways)
		public StringTokenizer (String theString);
		public StringTokenizer (String theString, String delimiters);
	Methods
		public boolean hasMoreTokens()
			This tests whether there are more tokens avilable from the tokenizer string
			When used in conjuction with nextToken, must check if there are more tokens to take in
		public String nextToken()
			Returns the next token from this tokenizer's string
		public String nextToken(String delimiters)
			First changes delimiter
			Then returns next token
			Modify the delimiters and tokenize with new delimiters (remains as the delimiters until next change)
		public int countTokens()
			Returns integers number of tokens remaining

	Some Related Notes
	--------

	How to convert a string to a number
		Java has methods like parseInt and parseDouble, in classes Integer and Double respectively
			These methods take as input a string and return the string as converted to the expected type
				They throw errors if the string cannot be converted
			These methods have a lot of overhead

			*/
				Integer.parseInt("17"); //returns 17 as an int
			 	Double.parseDouble("hey there"); //fail
			/*				

		Also you can make methods like your own tryParse to return boolean values of if parsing is possible

Regular Expressions (Regex)
--------
	Regular expressions allow us to denote a sequence of symbols (a pattern) that we wish to identify
		Gives us a means to perform pattern matching
		We could use complex patterns as delimiters instead of just single characters
		We can apply this pattern matching system to strings of any sort, to determine if a string matches a pattern we specify 
			Or if a pattern is found in it
		We can replace parts of strings matching a pattern
		A string (or part of the string) simply either matches the specified pattern, or it does not
		If a string matches a pattern (of potentially high, but not any level of complexity)...
			You can easily find pieces within the string that might be relevant to you 
				CHOMSKY HIERARCHY
	Regex is not only used in Java
		Unix and Windows grep functions (60-256)
		Automatic generation of Web page content
	Right now we will stick to relatively simple ways we can use this
		There are string methods that provide functionality for pattern matching and string manipulation using regex
			Even Scanner's delimiter is regex
				Regex is even more powerful than that
	When you specify a regular expression, you are specifying a pattern of characters
	A regular expression inside a pair of brackets is considered atomic
		ab is a regular expression, and so is (ab)
		Regex unary operators can be before or after their operand, depending on the operator
		A unary regex operator ' operating on (ab) operates on the string "ab"
			Whereas ' operating on ab (as in ab' or 'ab) only operates on a or b
			ab matches "ab"
			(ab)c matches "abc"
	Union operators are | or []
		a | b matches "a" or "b"
		[abc] matches "a" or "b" or "c"
		[0-9&&[^4567]] matches a single number 0, 1, 2, 3, 8, or 9
	Closure operator (*) means one or more occurences of an expression
		This operator occurs after the expression on which it operates
		Going back to the previous slide, ab* != (ab)*
			ab* means "a" followed by zero or more b
			(ab)* means zero or more (ab)
				(ab)* matches “”, “ab”, “abab”, ababab”
			[ab]* matches “”, “a”, “b”, “aa” “ab”, “ba”, “bb”, …
	One or more (+, after any expression)
		Similar to *, but this one is one or more
		a+ matches "a", "aa", "aaa"
	Zero or once (?, after any expression)
		a? matches "", "a"
	Range (-, between two characters inside [])
		[a-z] matches any lowercase letter
		[A-Z] matches any uppercase letter
		[0-9] matches any digit
	Complement (^ before any regular expression, only if it is at the beginning of [])
		Means not or except
		[^a] matches anything except a
		[^a-z] matches any symbol but lowercase letters
	Basic pattern examples:
		abc	exactly the sequence “abc”
		[abc]	any one of the letters “a”, “b”, or “c”
		[^abc]	any character except “a”, “b”, or “c”
		[a-z]	any single character from a to z, inclusive
		[a-zA-Z0-9]	any letter or digit
		ab+	“ab”, “abb”, “abbb”, “abbbb”, …
		(ab)+	“ab”, “abab”, “ababab”, …
		(ab)|(cd)	“ab”, “cd”
		a(b|c)d	“abd”, “acd”
		[abc]d	“ad”, “bd”, “cd”
		[A-Za-z]+[0-9]	any single letter followed by a digit
			When in doubt, use parantheses 
	Some predfined character classes
		“.”	any single character except line end (new line character)
		“\d”	any digit [0-9]
		“\D”	any non-digit [^0-9]
		“\s”	any whitespace
			\t horizontal tab character 
			\n newline character(linefeed)
				Linefeed advances downward to the next line (was repurposed to newline)
					Newline terminates the current line (not the same as separating)
			\x0B vertical tab character 
			\f form-feed character 
				Advance downwards to the next page/section (e.g. a page break)
			\r carriage return character 
				Return to the beginning of the current line without advancing downward
				On typewriters, a key/lever is pressed/pulled to move the carriage of an electric/mechanical typewriter...
					...back to a fixed position
		“\S”	any non-whitespace [^\s]
		“\w”	an alphanumericalcharacter [a-zA-Z_0-9 including _]
		“\W”	any non-alphanumerical character [^\w]
	Some boundary matchers
		These match the empty string if they are at the given position
			“^”	beginning of a line, we call this an anchor
			“$”	end of a line, we call this an anchor as well
			“\b”	a word boundary
			“\B”	not a word boundary
				A word boundary is the edge of a word (any whitespace)
		Examples:
			^(Lola).*
				“Lola is a good dog” matches
				“I found a tick on Lola last weekend” does not match
			.*(\bdog\b).*
				“Lola is a good dog” matches
				“Lola is a good doggie” does not match
			.*(\bplaying)$
				“Lola had fun playing” matches 
				“Lola had fun playing in the yard” does not match (playing is not last word)
				“Lola had fun displaying” does not match (dis- prefix before playing results in no word boundary)
	Double Backslashes
		Backslashes have a special means in strings, and we typically defined regex patterns as strings
		Thus in order to make use of backslashes in a pattern (or even to match a backslash literally), we need to use double slashes
			To escape the escape
		E.g. \n is an escape sequence for a newline, \b is an escape sequence for backspace
		“\b[a-z]+\b” is not going to match “\baaa\b”
			“\\b[a-z]+\\b” will match “\baaa\b”
				Which means as a string, it must be defined as “\\\\b[a-z]+\\\\b” (\\ means one \)
	Special characters such as parentheses, brackets, stars, plus signs – these are called metacharacters
		You need to use double backslash in order to escape before metacharacters, if you want the metacharacter to be taken literally
			If I want to match “a*”, I need to use the pattern “a\\*”
		It is actually legal to escape non-metacharacters in regex, which is a serious source of confusion
	Things get tricky when trying to match a string that needs a pattern interleaving metacharacters and regular characters
		The metacharacters may or may not need to be taken literally

Important Notes
--------
	Quantifier example
		Suppose your text is aardvark
			Using the pattern a*ardvark
				a* will match up to aa, but then “ardvark” won’t match
				The a* “backs off” and matches only a single “a”, allowing the rest of the pattern to correctly match
					It will "under the hood" try to find a working match
	Spaces are important!!
		A space is literally a space; when you put a space in a pattern it means that you are trying to match a space in the text string
		Very bad idea to put spaces in a regular expression in order to make it look pretty
	Repeating a regular expression a specific number of times
		X{N} repeat the regex X exactly N times
		X{N,} repeat the regex X at least N times
		X{N,M} repeat the regex X at least N times but no more than M times
			E.g.
				(AZ|CA|CO)[0-9]{4} matches AZxxxx, CAxxxx, and COxxxx, where x is a single digit

Pattern Matching Methods
--------
	These are found in the String class and use regex patterns to do cool stuff
		boolean matches(String regex)
			Returns true only if the whole calling string matches the regex pattern string
			See the considered sample for example usage
		String replaceAll(String regex, String replacement)
			Relatively new utility in the String class
			Allows us to replace all occurrences of a substring that matches the given regex pattern with a given replacement string







*/