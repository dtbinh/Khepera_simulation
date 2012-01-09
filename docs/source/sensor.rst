Sensor
======

The **Sensor Agent** is the agent which proceeds to different kind of
measurements using several sensors (infrared, ultrasound, ...).

Functionning
------------

In this simulation the measurements are not extracted from the
realworld, instead (to simplify the programming) we request the
**World** agent about a simulated representation of the world.

So, to measure a given world parameter (distance, temperature ...),
the **Sensor Agent** has to send a request (GET_POSITION, ...) to the
**World Agent** and to wait for the answer.

One it has collected some measurements, the **Sensor Agent** send it
to the **Controller Agent**.

Message: TSC
------------

The message **TSC** (Telegram from Sensor to Controller) is formated following the *JSON* standart.

Content::

	{
	"pv1": measure_value,
	"pv2": measure_value,
	"status_word": status word,
	"timestamp": timestamp_integer,
	...
	}
