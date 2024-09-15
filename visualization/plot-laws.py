import numpy as np
import matplotlib.pyplot as plt
import json

with open('sound-laws-from-phones.json', 'r') as f:
    sound_laws = json.load(f)

x = []
y = []
z = []
# lim = len(sound_laws)
lim = 1

for sound_law in sound_laws[0:lim]:
    x.append(sound_law['vector1'][0])
    y.append(sound_law['vector1'][1])
    z.append(sound_law['vector1'][2])

    x.append(sound_law['vector2'][0])
    y.append(sound_law['vector2'][1])
    z.append(sound_law['vector2'][2])

    x.append(sound_law['vector3'][0])
    y.append(sound_law['vector3'][1])
    z.append(sound_law['vector3'][2])

    x.append(sound_law['vector4'][0])
    y.append(sound_law['vector4'][1])
    z.append(sound_law['vector4'][2])


fig = plt.figure(figsize=(10, 10))
ax = plt.axes(projection='3d')
ax.grid()

ax.scatter(x, y, z, c=['r', 'g', 'k', 'k']*len(sound_laws[0:lim]), alpha=1)
for i in range(0, len(x), 4):
    ax.plot(x[i:i+2], y[i:i+2], z[i:i+2], c='r')
    ax.plot(x[i:i+3:2], y[i:i+3:2], z[i:i+3:2], c='k')
    ax.plot(x[i:i+4:3], y[i:i+4:3], z[i:i+4:3], c='k')

ax.set_title('Ad-hoc Sound Laws')

ax.set_xlabel('primary', labelpad=20)
ax.set_ylabel('secondary', labelpad=20)
ax.set_zlabel('tertiary', labelpad=20)

plt.show()
