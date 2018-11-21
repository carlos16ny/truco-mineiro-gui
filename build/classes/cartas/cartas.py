import os
import sqlite3

conn = sqlite3.connect("database/cartas.db")
cursor = conn.cursor()

cartas = []
cartas_novas = []

for i in os.listdir("images/cartas"):
	if(i.split(".")[1]=="png"):
            cartas.append(i.split(".")[0])
	else:
            continue

for i in cartas:
	cartas_novas.append(i.replace(" ", "_"))


for i in range (len(cartas_novas)):

        image = "images/cartas/" + cartas_novas[i] + ".png"
        param = (image, cartas[i])
        cursor.execute("UPDATE cartas SET img = (?) WHERE nome = (?)", param)

conn.commit()
