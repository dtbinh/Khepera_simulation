Controller
==========

The **Controller Agent** is the agent which receives measured data
from the **Sensor Agent**, wich process it and which decides an action
to do according to these data. Then the **Controller Agent** sends the
action to proceed to the **Actuator Agent**.

Functionning
------------

To complete

Message: TCA
------------

The message **TCA** (Telegram from Controller to Actuator) is formated following the *JSON* standart.

Content::

	{
	"u": action_to_proceed,
	"timestamp": timestamp_integer
	}
