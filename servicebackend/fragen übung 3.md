# Welches Relationship-Mapping ist optimal und warum (beschreiben)?

One to many, weil 1 Employee mehrere Services haben kann.

# Setze das beste Relationship-Mapping in deinem Microservice um.

okay

# Teste ob alles einwandfrei funktioniert.

ja

# Was passiert wenn man einen Employee löscht?

Falls er keinen Service hat funktioniert es, sonst erhält man eine exception, weil der service noch existiert.

# Was passiert wenn man einen Service löscht?

Der Service wird gelöscht.

# Was sind FetchTypes?

Bestimmen, ob der Foreign Key automatisch gejoined wird, oder erst wenn man ihn benötigt.

# Welche FetchTypes gibt es?

EAGER
LAZY

# Wie setzt JPA das Relationship-Mapping in SQL um?

Annotations OneToMany, ManyToOne, OneToOne, etc.
beim 1. Entity gibt man den Spaltennamen des Foreign Keys an, beim 2. den Namen der Tabelle

# Was macht @JoinColumn?

Es gibt den Spaltennamen des Primary Keys in der Tabelle des Foreign Entities an.
