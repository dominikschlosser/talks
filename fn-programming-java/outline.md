# Funktionale Programmierung für Java Entwickler - Ein pragmatischer Einstieg

## Bis Minute 5 - Einstieg

- Vorstellung DSc
    - SW-Entwickler/Architekt bei ISO-Gruppe
    - Hauptsächlich Enterprise-Java-Umfeld
    - Privat seit einiger Zeit FP interessiert, auch in beruflichem Umfeld nützlich und warum sehen wir später
    - Github, Twitter, ...
- Vorstellung ISO und Bereich
    - Mittelständische Firma in Nürnberg mit etwa 500 Mitarbeitern
    - Die einzelnen Firmen in der Gruppe sind weiter in autarke Bereiche aufgeteilt
    - Ich bin Teil des Bereichs der sich um Kunden in der öffentlichen Verwaltung kümmert (Consulting)
- Einstieg in Thema 
    - BILD: FP unemployable crazies image
    - Funktionale Programmierung wird von vielen noch rein akademisches Thema gesehen
    - In den letzten Jahren immer mehr Adaption im Mainstream
    - Trotzdem hohe Einstiegshürde, weil es "einfach anders" ist

## Bis Minute 10 - Motivation

- Warum sollte ich mich mit FP beschäftigen?
    - BILD: Alan Turing 1936 - Anything that is "effectively" computable can be computed by a Universal Turing Machine
    - BILD: aturingmachine.com
    - Weiß jemand was das ist?
    - Eine Turing Maschine - basierend direkt auf dem mathematischen Modell von Alan Turing, das er noch vor dem ersten lauffähigen Computer entwickelt hat
    - Beeindruckend in seiner Einfachheit: Symbole werden von Band gelesen und darauf geschrieben, einige Modelle erlauben auch ein Löschen. Abhängig von gelesenem Symbol und einer Tabelle von Instruktionen wird fortgefahren
    - Dieses Modell wurde bspw über die von-Neumann Architektur zur Umsetzung echter Computer verwendet: Hier gibt es Speicherzellen (Register) in die geschrieben/gelesen wird und eine CPU mit einem Instruktionsset (Instruktionstabelle)
    - Programmiersprachen bilden genau diese Architektur ab
- Unabhängig davon gibt es eine andere Herangehensweise an Programmierung
    - Lambda Calculus in den 1930ern und Kategorientheorie als Grundlage
    - Programm als mathematische Funktion: Input -> Output, referentielle Transparenz, keine Seiteneffekte
    - BILD: Alonzo Church 1933, 35 - Anything that is "effectively" computable can be computed by λ-calculus
    - TEXT: "Lambda calculus (also written as λ-calculus) is a formal system in mathematical logic for expressing computation based on function abstraction and application using variable binding and substitution. It is a universal model of computation that can be used to simulate any Turing machine." - https://en.wikipedia.org/wiki/Lambda_calculus
    - In Sprachen wie Lisp, Clojure, Scheme, Haskell usw. manifestiert
    - Fristete lange Zeit ein Schattendasein neben den Größen wie C, C++, Java, ...
- Kommt in der letzten Zeit immer mehr im Mainstream an: 
    - BILD (TODO)
    - F# populärer, C# Hybridsprache, seit Java 8 Lambda-Konstrukte und natürlich JavaScript!
    - React, Redux
    - Big Data!
- Wir werden uns in diesem Talk ein paar Konzepte aus der funktionalen Welt anschauen
    - Seht es als Erweiterung eurer Toolbox
    - Alles kann, nichts muss: FP im Rahmen von Java, trotzdem wird es hier und da Beispiele in anderen Sprachen geben (weil hier Java NOCH nicht so weit ist)

## Bis Minute 20 - Deklarativ statt imperativ

- BEISPIEL: Filtern einer Liste (imperativ) 
    - Erstmal fällt auf: Relativ viel Housekeeping. Ich erzeuge mir Listen für Input und Resultat und iteriere relativ häufig über diese Listen
    - for-Schleife: Typische off-by-one Fehlerquelle
    - Wie sieht es mit Erweiterbarkeit aus? Bspw. will ich, dass nur Zahlen zurückgegeben werden, die größer zehn sind und ich will die Ergebnisliste absteigend sortieren
- VS gleiches Beispiel in funktionalem Code
    - Sowohl die Erzeugung des Inputs, als auch das Filtern selbst ist deklarativ: Mir ist egal, wie die Implementierung es tut, es geht nur um das WAS
    - Erweiterbarkeit einfach gegeben: Ich kann Funktionen anhängen
- BILD: Was ist eine Funktion
    - Im Prinzip wie eine Prozedur in Java oder jeder anderen imperativen Programmiersprache: EVA
    - ABER: Keine Seiteneffekte! Eine Funktion muss bei gleichem Input immer den gleichen Output haben (PURE, Referentielle Transparenz)
        - D.h. keine Datenbank/Netzwerkcalls, kein Zugriff auf externen Zustand
        - GIF: <iframe src="https://giphy.com/embed/I8T1UHAIJgx8Y" width="480" height="247" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/cheezburger-wtf-cars-I8T1UHAIJgx8Y">via GIPHY</a></p>
    - Das ist der Part, der Neulinge anfangs häufig verschreckt bzw. mit dem sie nichts anfangen können
    - Ist auch logisch: In unseren Programmen kümmen wir uns selten um Statemanagement. Wir abstrahieren zwar in Layern bspw. von einer Datenbank, aber über Aufrufketten haben wir Datenbankzugriffe potentiell überall in unserem Programm!
        - Sehr schwer über Ursache->Wirkungsketten vernünftige Aussagen treffen zu können
        - Das gesamte Programm hängt von seinem Context, äußerem Zustand ab
        - TEXT: Joe Armstrong (Creator of Erlang): "Because the problem with object-oriented languages is they’ve got all this implicit environment that they carry around with them. You wanted a banana but what you got was a gorilla holding the banana and the entire jungle." 
    - Pure Functions sollen also die Komplexität des Codes reduzieren, indem Seiteneffekte verboten werden 
        - D.h. keine Datenbankcalls, kein Logging, kein Sysout, ...
        - ABER wie machen wir das? Ohne Seiteneffekte tut ein Programm nämlich nichts außer die CPU erhitzen
        - Seiteneffekte heißen in FP-Jargon auch einfach "Effects" und werden durch die Runtime behandelt => Erlang, Haskell, Elm, ...
        - BILD: elm-effects https://guide.elm-lang.org/architecture/effects/
        - Im Prinzip werden in rein funktionalen Sprachen Commands erzeugt, d.h. Containerobjekte, die beschreiben, was getan werden soll, es aber nicht selbst tun. Die Runtime kümmert sich dann darum
        - Vorteil: Fehlerpotential deutlich reduziert und an eine Stelle (Runtime) verlagert. Wie wir mit Fehlern im Code umgehen, sehen wir noch
        - In OO-Sprachen wie Java: TEXT: Functional core - imperative shell (auch clean architecture, ports and adapters) => Technik nach aussen verlagern um puren Kern zu erhalten
    

## Bis Minute 30 - Funktionale Konstrukte in Java

- TEXT: Funktionale Konstrukte in Java
    - seit Java 8 gibt es die sog. "Lambda Syntax" in Java
    - BEISPIEL: Anonymous Class -> Lambda
    - @FunctionalInterface für Compiler-Hint
    - Regeln: Eine non-static, non-default Methode
- Neben der neuen Syntax wurde das JDK an einigen funktional erweitert
    - BEISPIEL: Stream-API (map, sort, collect)
        - Terminal Functions
        - Lazy Stream Evaluation
- Beispiel Files-API, Comparator.comparing

## Bis Minute 40 - Optionals

- BILD: hoare, TEXT: "I call it my billion-dollar mistake. It was the invention of the null reference in 1965."
    - Als nächstes möchte ich über NULL sprechen, was die Probleme damit sind und welche Lösung uns FP anbietet
    - Wir werden dann gleich sehen, und so ist das oft in FP, dass die Lösung nur ein Vertreter eines viel größeren Konzeptes ist
    - Probleme mit Null: Fehlerquelle, bläht Code auf (wie wir gleich sehen werden), bricht java abstraktion und typsystem, keine Semantik (TEXT: "Absence of a signal should never be used as a a signal"" - J. Bigalow, 1947 ODER auch: aus falschem folgt beliebiges (aussagenlogik implikation))
    - BEISPIEL: Optional Imperative vs functional
- Wie funktioniert das nun? 
    - Railway oriented programming: BILD (TODO)
    - Function Chaining wobei abhängig vom Rückgabetyp unterschiedliche Dinge gemacht werden
    - Quasi Polymorphie und in Java auch genau so abbildbar: Mit Ableitungen
- Allgemein: Typsystem nutzen vs Primitive Obsession
    - BILD (TODO): Primitives wie String vs Datentypen mit Semantik wie Kundennummer
- In Java nicht möglich NULLs zu vermeiden => statische Codeanalyse und in der Zukunft mal Value Types (deshalb ist Optional nicht seralizable!)

## Bis Minute 50 - Das böse M-Wort

- Zurück zu Railway oriented programming
    - Optional ist tatsächlich nur ein Vertreter dieses Konzeptes, das auch "Monade" genannt wird
    - TEXT: Allgemein eine Struktur, die einen Wert in einen bestimmten Kontext bringt (auch: Wrapper)
    - Es gibt ein paar mathematische Anforderungen an Monaden
        - Eigenschaften: Links/Rechts Identität und Assoziativität
- TEXT: Weitere Monaden:
    - Tatsächlich Stream
    - Auch: List bspw selbst
- BEISPIEL: Errorhandling mit Try-Monade (selbstgeschrieben)
- BEISPIEL: Observables mit CompletableFuture

## Bis Minute 65 - Weiterführende Konzepte

- Funktionale Konzepte in Java noch nicht sehr tief verankert
- BEISPIEL: Mehrere Rückgabewerte / Tupel, joins, folds (jOOL)
- Sum und Product Types (TODO: Video mit einfacher Konzepterklärung finden, warum Pattern Matching fehlendes Puzzlestück ist)
- BEISPIEL: Pattern Matching (Beispiel mit Case Classes in Scala bzw. Java 11+ Konzeptcode)
    - Type Constructors, Extractors (unapply)
    - in OO: Visitor Pattern, das aber viel aufwändiger und unverständlicher ist

## Bis Minute 70 - Verabschiedung

- Weitere Tools für den Werkzeugkasten
- Erweitert den Horizont
- "Learn OO. Learn FP. Rise above them both."
