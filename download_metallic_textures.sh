#!/bin/sh
# Script : Téléchargement de textures futuristes réalistes métalliques

# Création des répertoires d'assets
mkdir -p assets/playerShip          assets/enemyShip          assets/doors_metallic          assets/corridors_metallic          assets/floors_metallic          assets/ceilings_metallic          assets/crosshair          assets/minimap          assets/bossArmor

# Player Ship - Panneaux métalliques (Poly Haven)
curl -L -o assets/playerShip/hull_panels_albedo.png \
  https://cdn.polyhaven.com/packs/hull_panels/hull_panels_albedo.png
curl -L -o assets/playerShip/hull_panels_normal.png \
  https://cdn.polyhaven.com/packs/hull_panels/hull_panels_normal.png
curl -L -o assets/playerShip/hull_panels_rough_metal.png \
  https://cdn.polyhaven.com/packs/hull_panels/hull_panels_rough_metal.png
curl -L -o assets/playerShip/hull_panels_ao.png \
  https://cdn.polyhaven.com/packs/hull_panels/hull_panels_ao.png

# Enemy Ship - Plaques métalliques sombres (AmbientCG)
curl -L -o assets/enemyShip/dark_alloy_albedo.png \
  https://ambientcg.com/data/download/dark_alloy_albedo.png
curl -L -o assets/enemyShip/dark_alloy_normal.png \
  https://ambientcg.com/data/download/dark_alloy_normal.png
curl -L -o assets/enemyShip/dark_alloy_rough_metal.png \
  https://ambientcg.com/data/download/dark_alloy_rough_metal.png
curl -L -o assets/enemyShip/dark_alloy_ao.png \
  https://ambientcg.com/data/download/dark_alloy_ao.png

# Doors - Holographic métallique (CC0 Textures)
curl -L -o assets/doors_metallic/holo_panel_albedo.png \
  https://cc0textures.com/view?id=Hologram&download=1
curl -L -o assets/doors_metallic/holo_panel_normal.png \
  https://cc0textures.com/view?id=Hologram_normal&download=1
curl -L -o assets/doors_metallic/holo_panel_rough_metal.png \
  https://cc0textures.com/view?id=Hologram_rough_metal&download=1
curl -L -o assets/doors_metallic/holo_panel_ao.png \
  https://cc0textures.com/view?id=Hologram_ao&download=1

# Corridors - Grunge métallique (Poly Haven)
curl -L -o assets/corridors_metallic/grunge_metal_albedo.png \
  https://cdn.polyhaven.com/a/grunge_metal/grunge_metal_albedo.png
curl -L -o assets/corridors_metallic/grunge_metal_normal.png \
  https://cdn.polyhaven.com/a/grunge_metal/grunge_metal_normal.png
curl -L -o assets/corridors_metallic/grunge_metal_rough_metal.png \
  https://cdn.polyhaven.com/a/grunge_metal/grunge_metal_rough_metal.png
curl -L -o assets/corridors_metallic/grunge_metal_ao.png \
  https://cdn.polyhaven.com/a/grunge_metal/grunge_metal_ao.png

# Floors - Plaques diamantées (AmbientCG)
curl -L -o assets/floors_metallic/diamondplate_albedo.png \
  https://ambientcg.com/data/download/diamondplate_albedo.png
curl -L -o assets/floors_metallic/diamondplate_normal.png \
  https://ambientcg.com/data/download/diamondplate_normal.png
curl -L -o assets/floors_metallic/diamondplate_rough_metal.png \
  https://ambientcg.com/data/download/diamondplate_rough_metal.png
curl -L -o assets/floors_metallic/diamondplate_ao.png \
  https://ambientcg.com/data/download/diamondplate_ao.png

# Ceilings - Vent metallic (CC0 Textures)
curl -L -o assets/ceilings_metallic/vent_panel_albedo.png \
  https://cc0textures.com/view?id=VentPanel&download=1
curl -L -o assets/ceilings_metallic/vent_panel_normal.png \
  https://cc0textures.com/view?id=VentPanel_normal&download=1
curl -L -o assets/ceilings_metallic/vent_panel_rough_metal.png \
  https://cc0textures.com/view?id=VentPanel_rough_metal&download=1
curl -L -o assets/ceilings_metallic/vent_panel_ao.png \
  https://cc0textures.com/view?id=VentPanel_ao&download=1

# Crosshair - HUD Holographique (Poly Haven)
curl -L -o assets/crosshair/hud_glow_albedo.png \
  https://cdn.polyhaven.com/a/hud_glow/hud_glow_albedo.png

# Minimap - Grille holographique (Poly Haven)
curl -L -o assets/minimap/holo_grid_albedo.png \
  https://cdn.polyhaven.com/a/holo_grid/holo_grid_albedo.png

# Boss Armor - Plaques renforcées (CC0 Textures)
curl -L -o assets/bossArmor/armor_plate_albedo.png \
  https://cc0textures.com/view?id=ArmorPlate&download=1
curl -L -o assets/bossArmor/armor_plate_normal.png \
  https://cc0textures.com/view?id=ArmorPlate_normal&download=1
curl -L -o assets/bossArmor/armor_plate_rough_metal.png \
  https://cc0textures.com/view?id=ArmorPlate_rough_metal&download=1
curl -L -o assets/bossArmor/armor_plate_ao.png \
  https://cc0textures.com/view?id=ArmorPlate_ao&download=1

echo "Téléchargement des textures futuristes réalistes métalliques terminé."
