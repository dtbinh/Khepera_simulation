World
=====

To simplify the simulation, the World, which is a representation of
the physical world, has been included in the sensor/actuator agent.

The sensor access it to measure some criteria, and the actuator access
it to update the world's state.

.. note::

   In the source code, there is no need to consider concurrent access
   issued, because each agents is run in a single thread. The
   different behaviours are runned by an ordonnancer which is included
   in each agent.

   Two behaviours on a single agent *cannot* be run simultaneouly.

Simulation
----------

This object is relevant only in the perspective of a simulation. When
the algorithm will be implemented for real application, it will be
useless to implement such a concept because the world will be
modelized by the real world and it will be accessed by using
measurement from sensors (infra-red sensor, ultra-sound sensor, ...)
and will be modified by the actuator (engine ...).
