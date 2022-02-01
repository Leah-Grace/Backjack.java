##README

Critical next steps:
Reconsider logic - Why are calculations made within the Actor class? Should calculations be moved to the deck class?
Magic numbers - Eliminate magic numbers in calculations of card values.

The program is currently written with the OOP paradigm, but it may be more appropriate for a table game to be written with the event-driven paradigm.
The game has a deck, a table, a player and a dealer however the game manipulates these classes based on events and decisions.

It may be appropriate to shift the game from the current classes to events:
    - Initiate the game - create classes and allocate cash to player
    - Start the game/Round One - Each player gets two cards, calculate value and make decision.
    - Round Two - Execute according to decision, calculate values and make decision.
    - Round Three - Repeat Round two logic.
    - Round Four - Repeat Round two logic.
    - Round Five - if anyone makes it to round five w/ points below 21 then end the game, and they win.

** Deck functionality: Cards are chosen randomly from the deck but functionality can be improved with consideration to the following:
    - Blackjack is played with at least 4 decks
    - Selected cards should be removed from the deck, at present there is an infinite chances of selecting each card.
