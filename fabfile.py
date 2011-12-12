#!/usr/bin/env python
#-*- coding: utf-8 -*-

from fabric.api import *

# constant
classpath = [
    'bin',
    'lib/jade.jar',
    'lib/json/json_simple-1.1.jar'
    ]
destdir = 'bin'

# tasks definition
@task(default=True)
def all():
    """
    """
    build()
    doc()

@task
def build(xlint='no'):
    """
    Build the project
    """
    local('javac -cp ' + ':'.join(classpath)
          + ' -d ' + destdir
          + ' src/*.java '
          + ' -Xlint' if xlint == 'yes' else ''
        )

@task
def run():
    """
    Run the simulation.
    """
    local('java -cp ' + ':'.join(classpath) + ' Launch')

@task
def doc(target='html', browse_='no'):
    """
    Generate the sphinx documentation.
    """
    with lcd('docs'):
        if target == 'html':
            local('make html')
        elif target == 'pdf':
            local('make latexpdf')
    if browse_.lower() in ('yes', 'y'):
        browse(target)

def browse(target='html'):
    """
    Browse the documentation.
    
    Arguments:
    - `target`: 'html' or 'pdf'
    """
    path_doc = 'docs/build/'
    if target == 'html':
        path_doc += 'html/index.html'
    elif target == 'pdf':
        path_doc += 'latex/Kheperasimulation.pdf'
    print('xdg-open ' + path_doc)
    local('xdg-open ' + path_doc)

@task
def clean():
    """
    """
    local('rm -rf ' + destdir + '/*')
