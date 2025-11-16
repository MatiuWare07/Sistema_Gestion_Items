### Nintendo Items Manager
A comprehensive inventory management system for items from Super Mario Bros and The Legend of Zelda universes, built with Java and featuring a modern Swing GUI.
--- 

## 📋 Table of Contents

# About the Project
Features
Technologies
Project Structure
Getting Started

Prerequisites
Installation
Running the Project


Usage
Classes Overview
Contact
---

## 🎮 About the Project
Nintendo Items Manager is an Object-Oriented Programming (OOP) project developed in Java that implements a complete item management system for two iconic Nintendo franchises: Super Mario Bros and The Legend of Zelda.
The project demonstrates key software development concepts including:

- Inheritance and Polymorphism
- Abstract Classes and Interfaces
- Collections Framework
- Exception Handling
- GUI Development with Swing
- Clean Code Architecture

This project was developed as part of the DAM (Desarrollo de Aplicaciones Multiplataforma) curriculum in Madrid, Spain.

## ✨ Features
Core Functionality

- ✅ Dual Universe System: Separate inventories for Mario and Zelda items
- ✅ Item Creation: Create different types of items specific to each universe
- ✅ Inventory Management: Add, remove, and clear items with capacity control
- ✅ Item Actions: Use items with specific behaviors (attack, heal, transport, etc.)
- ✅ Search & Filter: Find items by name, rarity, or universe
- ✅ Durability System: Weapons and shields degrade with use and can be repaired
- ✅ Exception Handling: Custom exceptions for full inventory scenarios

### Mario Items

- Power-Ups: Super Mushroom, Fire Flower, Super Star, Cape Feather
- Coins: Collectible currency with customizable amounts
- Pipes: Transport items with destinations (normal and secret)

### Zelda Items

- Weapons: Master Sword, Kokiri Sword, Biggoron Sword, etc.
- Shields: Hylian Shield, Deku Shield, Mirror Shield
- Potions: Red, Green, and Blue potions with healing effects
- Rupees: Currency in different colors (Green, Blue, Red, Purple, Silver, Gold)

## GUI Features

- 🎨 Modern Dark Theme with Nintendo-inspired colors
- 🖱️ Interactive Buttons with hover effects
- 📊 Real-time Capacity Tracking
- 📝 Detailed Information Panel
- 🎯 Tab-based Navigation between Mario and Link inventories
- ⚡ Smooth User Experience with confirmation dialogs


## 🛠️ Technologies

- Language: Java 15+
- GUI Framework: Java Swing
- Build Tool: Maven (optional) / Manual compilation
- IDE: IntelliJ IDEA Community Edition (recommended) / Eclipse
- Architecture: MVC-inspired (Model-View separation)
- Design Patterns:

- Factory Pattern (item creation)
- Observer Pattern (list updates)
- Strategy Pattern (item behaviors)




## 📁 Project Structure
SistemaGestionItems/
│
├── src/
│   └── com/
│       └── nintendo/
│           └── items/
│               │
│               ├── enums/
│               │   ├── Rareza.java
│               │   ├── TipoEfecto.java
│               │   └── UniversoJuego.java
│               │
│               ├── interfaces/
│               │   └── IItem.java
│               │
│               ├── base/
│               │   └── Item.java
│               │
│               ├── mario/
│               │   ├── ItemMario.java
│               │   ├── PowerUp.java
│               │   ├── Moneda.java
│               │   └── Tuberia.java
│               │
│               ├── zelda/
│               │   ├── ItemZelda.java
│               │   ├── Arma.java
│               │   ├── Escudo.java
│               │   ├── Pocion.java
│               │   └── Rupia.java
│               │
│               ├── inventario/
│               │   ├── Inventario.java
│               │   └── InventarioLlenoException.java
│               │
│               ├── gui/
│               │   └── InventarioGUI.java
│               │
│               └── Main.java
│
└── README.md
---
## 🚀 Getting Started
Prerequisites

- Java Development Kit (JDK) 15 or higher
- IntelliJ IDEA (recommended) or Eclipse
- Basic knowledge of Java and OOP concepts

## Installation

1. Clone the repository

bash   git clone https://github.com/yourusername/nintendo-items-manager.git
   cd nintendo-items-manager

2. Open the project in your IDE

IntelliJ IDEA: File > Open > Select project folder
Eclipse: File > Import > Existing Projects into Workspace


3. Verify JDK configuration

4. Ensure your IDE is using JDK 15 or higher
Set the project SDK if needed



# Running the Project
Option 1: GUI Version (Recommended)
From Command Line:
bash# Navigate to src directory
cd src

# Compile all classes
javac com/nintendo/items/**/*.java

# Run the GUI
java com.nintendo.items.gui.InventarioGUI
From IntelliJ IDEA:

Navigate to InventarioGUI.java
Right-click on the file
Select Run 'InventarioGUI.main()'

Option 2: Console Version
From Command Line:
bash# Navigate to src directory
cd src

# Compile all classes
javac com/nintendo/items/**/*.java

# Run the console version
java com.nintendo.items.Main
From IntelliJ IDEA:

Navigate to Main.java
Right-click on the file
Select Run 'Main.main()'


## 💻 Usage
- 1. GUI Application

- 2. Launch the application

- 3. The main window will open with two tabs: MARIO and LINK


## Create items

- 1. Click on the creation buttons on the left panel
- 2. Select the item type from the dropdown
- 3. Follow the prompts to customize your item


## Manage inventory
Select an item from the list
Use action buttons on the right:

Usar (Use): Activate the item's effect
Info: View detailed item information
Eliminar (Delete): Remove the item from inventory
Limpiar Todo (Clear All): Empty the entire inventory

--- 


## View information

Check the bottom panel for detailed feedback
Monitor capacity in the inventory header (e.g., "5/20")



## Console Application
The console version demonstrates all features through automated tests:

- Creates sample items
- Shows inventory contents
- Tests item usage
- Demonstrates durability system
- Tests exception handling


### 📚 Classes Overview
# Enumerations

- Rareza: Item rarity levels (Common, Rare, Epic, Legendary)
- TipoEfecto: Effect types (Healing, Attack, Defense, Speed, Invincibility, Currency, Transport)
- UniversoJuego: Game universes (Mario, Zelda)

# Base Classes

- IItem: Interface defining the item contract
- Item: Abstract base class with common attributes

# Mario Universe

- ItemMario: Abstract class for Mario items (adds duration attribute)
- PowerUp: Power-ups with effects and multipliers
- Moneda: Collectible coins
- Tuberia: Transport pipes with destinations

# Zelda Universe

- ItemZelda: Abstract class for Zelda items (adds level requirement)
- Arma: Weapons with damage and durability
- Escudo: Shields with defense and durability
- Pocion: Potions with healing effects
- Rupia: Currency with color-coded values

# Management

- Inventario: Inventory management with capacity limits
- InventarioLlenoException: Custom exception for full inventory

# User Interface

- InventarioGUI: Modern Swing-based graphical interface
- Main: Console-based demonstration
--- 
## 👤 Contact
Mateo Fitipaldi

GitHub: @MatiuWare07
LinkedIn: https://www.linkedin.com/in/mateo-fitipaldi/
Email: fitipaldimateo85@gmail.com

## 🙏 Acknowledgments

- Inspired by Super Mario Bros and The Legend of Zelda by Nintendo
- Developed as part of DAM (Desarrollo de Aplicaciones Multiplataforma) curriculum
- Thanks to the Java and Swing communities
- Special thanks to my instructors and classmates at DAM Madrid


## 📖 Additional Resources

- Java Documentation
- Java Swing Tutorial
- Object-Oriented Programming in Java
- IntelliJ IDEA Documentation


Made with ❤️ by a DAM student in Madrid, Spain

