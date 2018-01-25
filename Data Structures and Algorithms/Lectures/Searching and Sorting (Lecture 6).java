/* Stacks Linked List Implementation (Lecture 6) 2017-09-21

If data keeps changing, it doesn't make sense to use binary search
	If the data only needs to be sorted once, use binary search
	In order to do binary search
		 Know the first value in the array and the last value in the array (to know the range)
	Binary search is known as decrease and conquer

Interpolation Search
	The interpolation search try to follow the way we humans search for a name ina  phone bookor a word in a dinctionary
		Rather than calculating the middle of the array or etc, we calculate the position where the item will be close to
	Loc = Begin + ((End - Begin) / (List[end] - List[Begin])) * (key - List[Begin])
		This is an actual location function, but note that it assumes an even distribution of numbers
	Self assessment questions are similar to midterm questions 