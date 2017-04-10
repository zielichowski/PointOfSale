Implement a simple point of sale.
Assume you have:
<br />– one input device: bar codes scanner 
<br />– two output devices: LCD display and printer
<br />
<br />Implement: 
<br />– single product sale: products bar code is scanned and: 
<br />– if the product is found in products database than it's name and price is printed on LCD display 
<br />– if the product is not found than error message 'Product not found' is printed on LCD display 
<br />– if the code scanned is empty than error message 'Invalid bar-code' is printed on LCD display 
<br />– when 'exit' is input than receipt is printed on printer containing a list of all previously scanned items names and prices as well as total sum to be paid for all items; the total sum is also printed on LCD display
<br /><br />Rules: 
<br />– use only SDK classes and your favorite test libraries 
<br />– mock/stub the database and IO devices 
<br />– concentrate on proper design and clean code, rather than supplying fully functioning application
