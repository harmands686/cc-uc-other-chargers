This service listen to bulk orders from the messaging queue. And split the bulk order to various child order depedending on certain criteria like (no. of subscribers) and save split child order to database.
Bulk order : It is reffered to orders where a single customer order xml contains multiple subscriber order under it. Example enterprise customer placing an order with telco to get new mobile connections for all its employee. In such cases a single order will contain all subscribers (employee) info.

Input - XML/RESTWS
Output - Save order to database after splitting



delivery pipeline test1