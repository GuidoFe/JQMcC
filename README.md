# JQMcC
**OBIETTIVO:**
Riproduzione in linguaggio java dell'algoritmo di Quine - McClusky per la minimizzazione del costo di una funzione 
completamente speificata a un singolo output.

**STRUTTURA DATI INPUT:** Lista di mintermini in forma decimale, uso di un convertitore da int decimale a stringa in binario.

**DA MODIFICARE PER JAVA**

```c
typedef struct {
	list_c coppie;
	char espansione[10];
	boolean marked;	
}el_esp;
```

**PASSAGGIO Espansione -> Copertura**
**DA MODIFICARE**
```c
typedef struct {
    char espansione[10];
    list_c listMin;
}Implicante;

typedef struct _listP{
    Implicante P;
    _listP * next;
} listP;
```

**STRUTTURA OUTPUT:** lista di char.
