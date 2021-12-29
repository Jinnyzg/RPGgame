# Legends: Heroes and Monsters

_Name: Jingyu Zhang  
Email: jingyz@bu.edu  
BUID: U33927556_

## Environment
1. OS: Windows
2. Ide: IntelliJ

## Compilation instruction
(in src dir)
1. javac *.java
2. java Main

## Game Mechanism
1. When heroes are equipped with armor, the hurt would be  reduced by defense stat*0.05 of the armor
2. During fight, alive heroes and alive monsters rotate after each fight
3. since the market is created with different inventories, which only contains some items(choose randomly). So when heroes have low level, they may find they cannot buy anything in a market. But this is resonable, since this means they have to explore other markets to buy items under their level.

## Bonus Points
1. color console output to show different mode the player are now under
2. use parseText class to parse the configuration files
3. develop an integrated GUI with different Game Views interface
4. Design Pattern
   1. Use Factory Pattern: Develop a WorldFactory which can create a balanced but randomized world with all needed related entities.
      Since the behavior of the Factory of this game need to obey are defined by the FactoryCreator interface, we can use other Factory classes which implemented this interface
      to create different worlds with different features(E.g. Concrete, world only contain low levels characters)
   2. Use Design Pattern:
      1. design multiple Game mode that separately manage different types of game actions, and the behaviors of different modes needed to obey are defined by interface.
         So we can change the implementation of all games modes easily if only some parts of the logics of the game are changed.
      2. develop an Integrated GUI: ViewOpponent and LayoutManager manage to create and layout the opponents in Game view separately,
         so we can change the rules of layout or opponent forms freely
   3. Use MVC Pattern: Users interact with interactive classes and these interactive classes can send the input and output between the internal and players.

## Classes and Interfaces

### WorldComponent

1. WorldComponent: A class defines all objects that exist on the world map
2. ComponentType: A enum class that specifies all types of the World component
3. Road: A subclass of WorldComponent, representing a normal accessible component on the map
4. Stone: A subclass of WorldComponent, representing a place that is inaccessible for heroes

### Map

1. Tile: A cell in the 2d grid map, which can contains multiple worldComponents in it.
2. Map: A class defines a two-dimensional grid world made by Tiles. It also implements Displayable interface, which
   represents it can be displayed in the Integration GUI.

## Character

1. Character: An abstract class that defines all characters basic state and method in the RPGgame. Also, it extends the
   WorldComponent since all characters can be placed on the map.
2. Hero: A class that extends Character that defines the states and behaviors of heroes(player controlled). It also
   implements Attackable and Displayable.
3. HeroType: An enum class that defines all hero types and also store the favored skills of every hero type.
4. HeroTeam: A class that defines a group of heroes.
5. Monster: A class that extends Character that defines the states and behaviors of monsters. It also implements
   Attackable and Displayable.
6. MonsterType: An enum class that defines all monster types.
7. MonsterTeam: A class that defines a group of monsters.
8. Skill: A class that defines the skill of characters
9. SkillType: An enum class that defines all the skill types of characters

## Item and Market

1. Tradable: an interface that defines the behavior of a tradable item(can buy and sell)
2. Item: A super class that defines all items in the world, which implements Tradable interface
3. ItemType: A enum class that defines all item types, and each type stores a key(Integer), then we can use the key to
   get the ItemType easily
4. Castable: an interface that defines the behavior of a castable item(can cast)
5. Spell: A class that extends Item defines spell, and it implements the Castable Interface
6. SpellType: An enum class that defines all spell types and also store the skill it can hurt
7. Equipable: A generic interface defines the items that can be equipped by characters(can equip), and the generic type
   T extends Character defines which type of character can equip it.
8. Weapon: A class extends Item that defines weapon used in the game, and it implements the Attackable and
   Equipable<Hero> Interface(only hero can equip it)
9. Armor: A class extends Item that defines armor used in the game, and it implements the Attackable and Equipable<Hero>
   Interface(only hero can equip it)
10. Usable: a generic interface that defines the behavior of an item that can be used for once(can use), and the generic
    type T extends Character defines which type of character can use it.
11. Potion: A class extends Item that defines armor used in the game, and it implements the Usable<Hero> Interface(only
    hero can use it)
12. Market: A subclass of WorldComponent, representing a place where heroes can buy and sell their items, and it has its
    own inventory

## Factory

1. FactoryCreator: An interface that defines a factory's behavior of the RPGgame
2. WorldFactory: a class that can create all needed entities of the world and implements FactoryCreator interface. 
   This Factory tends to create a randomized but balanced world:
   1. This Factory generate market contains random items but types of them are balanced, 
   2. This Factory generate heroTeam and MonsterTeam random characters with different types

## Parse

1. ParseText:a class that is used to process and parse all the configuration files

## Game Mode
1. IMoveMode: This interface defines the behavior a moveMode in this game need to obey
2. MoveMode: This class defines the basic move mode of this game, with methods to manage move action
3. ActionType: This enum class defines all action the player can choose in the fight mode, and each type stores a key(Integer), then we can use the key to get the actionType easily
4. IFightMode: This interface defines the behavior a fightMode in this game need to obey
5. FightMode: This class define the fight mode of this game, with methods to manage fight action
6. ITradeMode: This interface defines the behavior a tradeMode in this game need to obey
7. TradeMode: This class define the trade mode of this game, with methods to manage trade action

## Interactive class

1. Displayable: this Displayable interface defines the behavior of objects that can be managed by LayoutManager(implements method toStringList)
2. ViewComponent: this Class generate the component that can be added to the background and displayed by LayoutManager
3. LayoutManager: this layoutManager class manages to put multiple view component in a background and display the background
4. InteractionManager: this class manages all the input actions of users to get proper and valid input
5. GameView: this classes create different specific views of this game for different modes and different types of output

## Game

1. Game: an abstract super class for all games
2. LegendsGame: a subclass of Game that can play legends
3. Main: launch the game
