#!/bin/sh
# Télécharge les textures futuristes métalliques

mkdir -p assets/playerShip assets/enemyShip assets/doors_metallic \
         assets/corridors_metallic assets/floors_metallic assets/ceilings_metallic \
         assets/crosshair assets/minimap assets/bossArmor

# Player Ship - Panneaux métalliques (Poly Haven)
curl -L -o assets/playerShip/hull_panels_albedo.png https://cdn.polyhaven.com/packs/hull_panels/hull_panels_albedo.png
curl -L -o assets/playerShip/hull_panels_normal.png https://cdn.polyhaven.com/packs/hull_panels/hull_panels_normal.png
curl -L -o assets/playerShip/hull_panels_rough_metal.png https://cdn.polyhaven.com/packs/hull_panels/hull_panels_rough_metal.png
curl -L -o assets/playerShip/hull_panels_ao.png https://cdn.polyhaven.com/packs/hull_panels/hull_panels_ao.png

# Enemy Ship - Plaques métalliques sombres (AmbientCG)
curl -L -o assets/enemyShip/dark_alloy_albedo.png https://ambientcg.com/data/download/dark_alloy_albedo.png
curl -L -o assets/enemyShip/dark_alloy_normal.png https://ambientcg.com/data/download/dark_alloy_normal.png
curl -L -o assets/enemyShip/dark_alloy_rough_metal.png https://ambientcg.com/data/download/dark_alloy_rough_metal.png
curl -L -o assets/enemyShip/dark_alloy_ao.png https://ambientcg.com/data/download/dark_alloy_ao.png

# Doors - Holographic métallique (CC0 Textures)
curl -L -o assets/doors_metallic/holo_panel_albedo.png https://cc0textures.com/view?id=Hologram&download=1
curl -L -o assets/doors_metallic/holo_panel_normal.png https://cc0textures.com/view?id=Hologram_normal&download=1
curl -L -o assets/doors_metallic/holo_panel_rough_metal.png https://cc0textures.com/view?id=Hologram_rough_metal&download=1
curl -L -o assets/doors_metallic/holo_panel_ao.png https://cc0textures.com/view?id=Hologram_ao&download=1

echo "Téléchargement des textures terminé."