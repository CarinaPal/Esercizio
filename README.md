# Esercizio

## Name
Esercizio.\

## Branches

```
main  
	|_ develop 
		|_ feature 
			|_ nome-feature-01 
			|_ nome-feature-02 
```			
Il progetto segue il gitflow. \
Main possiede l'ultimo codice aggiornato e considerato "in produzione". \
Develop è il branch più aggiornato ed è su quello in cui si trovano le ultime feature stabili e funzionanti che potrebbero finire su main. \
Feature/nome-feature-XX sono i branch che partono da develop e dove si sviluppano le evolutive o si sistemano alcuni bug. \
Per i merge si esegue un 2-way-merge dove prima si fa un merge del branch padre al figlio, si risolvono i conflitti (se presenti) e poi si fa un merge dal branch figlio al padre.


## Description
Questo progetto ha l'obiettivo di sviluppare un'API di tipo CRUD per la gestione dell'entità User.

È prevista una funzionalità di ricerca degli utenti con la possibilità di applicare filtri per nome, cognome o entrambi.

Il sistema utilizza MongoDB come database.

L'infrastruttura del progetto include la creazione di un'immagine Docker mediante un Dockerfile, e il database viene eseguito all'interno di un container Docker.

Infine, è disponibile un'API dedicata all'importazione dei dati: essa accetta in ingresso un file user.csv e carica le informazioni all'interno del database.

## Installation
Jetbrains IntelliJ IDEA è l'IDE consigliato per aprire e gestire il progetto. \
L'applicazione è stata compilata in Java 17 quindi una versione di Java 17 (o superiore) deve essere installata sul PC o tramite IntelliJ. \
L'applicazione fa uso di Maven come gestore del progetto e delle dipendenze. 

## Usage
Il progetto riceve in input i seguenti file: \	
	- users.csv \
Il file si trova nella root del progetto.


## Project status
01/04/2025 - Al momento il progetto è completo. 


