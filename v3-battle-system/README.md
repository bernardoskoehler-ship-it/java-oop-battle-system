# Java RPG V3 Battle System

A turn-based RPG combat system made in Java as a personal learning project focused on Object-Oriented Programming (OOP), combat mechanics, and game logic.

This project was created to practice Java fundamentals while building a functional text-based RPG battle system.

---

# Features

- Turn-based combat
- Player vs Enemy battles
- Weapons with different damage and stamina costs
- Armor system with durability
- Critical hit system
- Miss hit system
- Stamina management
- Healing system
- Enemy AI behavior
- Randomized enemy equipment and names
- Status display system

---

# Technologies Used

- Java
- Object-Oriented Programming (OOP)
- HashMap
- Scanner
- Random

---

# Combat Mechanics

## Weapons

Each weapon has:
- Damage
- Stamina consumption

Current weapons:
- Dagger
- Sword
- Axe

---

## Armor

Armor absorbs part of the damage and loses durability over time.

Current armor types:
- Light Armor
- Medium Armor
- Heavy Armor

---

## Stamina System

Actions consume stamina:
- Weak attacks cost less stamina
- Strong attacks cost more stamina
- Resting restores stamina

---

## Critical Hits

Attacks have a chance to deal critical damage.

---

## Enemy AI

Enemies can:
- Choose between weak and strong attacks
- Rest when stamina is low
- Heal themselves when health is critical

---

# Project Structure

```text
src/
 ├── Personagem.java
 ├── Jogador.java
 ├── Inimigo.java
 ├── SistemaCombate.java
 ├── Armas.java
 ├── Armaduras.java
 ├── Menu.java
