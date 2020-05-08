Red Hat Hackathon Plus
Usecase - Telecom Domain Order Management Process : BulkOrderHandling

Description - Some Order managment systems have limitation to process Bulk orders. Because of this a custom bulk handling layer need to be created between order source and OMS to split the bulk order before sending it to OMS.

Bulk order definition in our usecase- It is reffered to orders where a single customer order xml contains multiple subscriber orders under it. Example enterprise customer placing an order with telco to get new mobile connections for all its employee. In such cases a single order will contain all subscribers (i. employees) info.
