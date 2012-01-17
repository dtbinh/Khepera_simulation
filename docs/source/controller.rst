Controller
==========

The **Controller Agent** is the agent which receives measured data
from the **Sensor Agent**, wich process it and which decides an action
to do according to these data. Then the **Controller Agent** sends the
action to proceed to the **Actuator Agent**.

Functionning
------------

The goal of the controller is to consider messages from the sensor and
to decide what to do based on these messages.

Then, the controller has to send its decision to the actuator which
will execute it.

Message: TCA
------------

The message **TCA** (Telegram from Controller to Actuator) is formated following the *JSON* standart.

Content::

	{
	"u": action_to_proceed,
	"timestamp": timestamp_integer
	}

Simulation
----------

In our case, the controller will receive the message from the sensor,
it will decide in which direction the sensor/actuator agent must go
and how long it has to move.

Then the controller will send its order to the actuator.
