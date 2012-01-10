Install
=======

Build instructions
------------------

We assume that you have a working Java developper environment (**JDK**
and **JRE**). If not, you can download it for free on the `official Java website`_.

.. _`official Java website`: http://www.oracle.com/technetwork/java/javase/downloads/index.html


You can (re)build the project by running the following commands::

    $ make build
    $ make run

.. note::

    *make* is a standard Unix utility. You can find it on every Unix
    system (Linux for example). Since the project is Java-based, it's
    portable, so you can use it on every Java-supported platform.


The documentation have been produced using the tool *Sphinx*. You can
download it for free on the `sphinx website`_.

You can (re)build the documentation (the same one you are currently
reading) by running the following commands::

    $ cd docs
    $ make html
    $ make latexpdf

.. _`sphinx website`: http://sphinx.pocoo.org/

The documentation is built in the directories
*docs/build/html/index.html* and *docs/build/latex*.
