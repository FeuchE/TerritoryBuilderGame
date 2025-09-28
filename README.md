# Territory Forge: Project Documentation
Grow your territory, assign villagers wisely, and shape the fate of your kingdom. 

## 🌍 Project overview
A text-based resource management game demonstrating object-oriented design and interactive system building. A strategy simulation showcasing clean Java architecture, user interaction handling, and game logic design. 
‍
- 🎯 **The goal:** Create a Utopia clone
- 🛠 **Tech-stack:** Java
- 🛠 **Tools:** DB Shema
‍
- 📆 **Dates:** Dates: August 2025
- 📆 **Duration:** 2 weeks 

---

## 📣 The pitch

**The problem:**
Strategy fans often want the depth of managing resources, characters, and combat, but without overwhelming complexity.  

**The solution:**
This strategy game delivers a streamlined system where players build territories, assign villagers, and manage resources, while still offering tactical depth through customisable knight stats and role-based assignments.    

**The target audience:**
- Demographic: 10-40 year old retro game lovers.  
- Casual to mid-core strategy players who enjoy management and tactical decision-making without needing to learn overly complex mechanics. 

**The features:**
- Object-oriented Java backend managing territories, villagers, buildings, and resources.
- Villager assignment and unassignment logic, with role-based validation (e.g., knights → barracks).
- Dynamic knight stat modification with validation rules (e.g., max total = 100).
- Resource production system linked to building types.
- User-friendly console interactions with error handling and menu navigation. 

---

## 🚧 Challenges

### What did I struggle with?

**1. Game play**
- **Challenge**: Managing dynamic territory structures with villagers, buildings, and resources. 
- **Solution**: Designed an object-oriented backend with modular classes for Territory, Villager, Building, and Resource management.

**2. User Experience**
- **Challenge**: Creating an intuitive player experience. 
- **Solution**: Built a menu-driven system with clear prompts and error handling. 

**3. Validations**
- **Challenge**: Preventing invalid villager assignments (e.g., knights in farms). 
- **Solution**: Implemented role-based validation logic ensuring only correct villager–building pairings.

**4. Error handling**
- **Challenge**: Balancing knight stats while keeping gameplay fair. 
- **Solution**: Introduced validation to cap total stat points at 100, with error handling and retry prompts.  

---

## 🏆 Accomplishments

### What did I learn?

**✅Technical Skills:**
- Built a fully functional, object-oriented game backend in Java with clean modular design.
- Implemented validation systems to enforce balanced gameplay (e.g., villager roles, knight stat caps).
- Developed dynamic territory management supporting buildings, villagers, and resource production.
- Designed a user-friendly console interface with clear menus, error handling, and navigation flexibility.
- Applied software engineering best practices, including separation of concerns and scalable class structures

**✅Problem-Solving:**
- Used DB schema to visualise the back-end structure
- Breaking down large problems into actionable solutions
- Prioritizing features for maximum impact

---

## 💡 Planning

**DB schema:**
I planned the DB schema for the game. This helped me visualise the back-end structure and ensure each model table contained the correct columns, with the corresponding data-type. This also enabled me to confirm foreign keys.

---

## 🔧 Building the back-end

**Concept & Design:**
- Defined the core gameplay loop (manage territories, construct buildings, create villagers and assign villagers to buildings.
- Outlined main entities (Territory, Building, Villager).
- Planned object-oriented structure with separation of concerns.

**Core Classes & Data Structures :**
- Implemented base classes for Territory, Building, and Villager, with subclasses for specialized roles (e.g., Knight, Farmer, Blacksmith).
- Created relationships (e.g., Villagers can be assigned/unassigned to Buildings, Buildings belong to Territories).

**Game Logic Implementation:**
- Added rules for villager assignments (only specific roles allowed in certain buildings).
- This handled validation errors, to stop users entering invalid data by assigning villagers to the incorrect type of building.

**Resource management:**
- Developed resource production system triggered by buildings.

**Adjustable knight stats:**
Built validation rules (e.g., knight stats capped at total of 100 points).

**User Interaction (Console UI):**
- Designed menu-driven navigation for all major actions (add, remove, assign, modify, view).
- Implemented robust input handling with error messages for invalid inputs.

**Gameplay Depth:**
- Enabled dynamic modification of knight stats within balance rules.
- Added error handling to stop users entering stats total over 100 points, with explanitory messages to the user.

**Automatically unassign villagers:**
- Ensured villagers automatically unassign when buildings are removed using a for loop.

**Territory structure printouts:**
- Included detailed printouts of structures and knight stats for clarity using nested for loops.

**Testing & Iteration:**
- Manually tested each feature to confirm correct game logic and error handling.
- Refactored functions for clarity and maintainability (e.g., assignment validation, stat updates).

---

## 🚀 Future plans

As this was a two week sprint I had to prioritse the key user journey, below is a list of features I would like to add in the future to improve the user experience:
- Gold system, where players mine for gold to be able to purchase buildings and villagers.
- Added option to exit tasks and return to the main menu for better UX.
- Add game persistence so players can resume where they left off. 
- Build a React or Vue.js frontend and connect with your Java backend. 
- Compete against other kingdoms (AI-controlled or player-vs-player). Let knights battle invaders or rival kingdoms, using their stats (HP, stamina, strength, defense). 
- Implement villager leveling (e.g., farmers produce more over time, knights gain strength after battles). 
- Allow upgrading buildings for efficiency (e.g., a Farm produces more food at level 2). 
- Add chance-based challenges (plagues, natural disasters, raids) to make strategy matter.
  
