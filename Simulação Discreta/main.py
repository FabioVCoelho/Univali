from random import uniform
import math

escolha = 0

while(escolha!=5):

	print("Escolhe o tipo de distribuição que você deseja usar. ")
	print("1 - Uniforme")
	print("2 - Triangular")
	print("3 - Exponencial")
	print("4 - Normal")
	print("5 - Sair")
	escolha = int(input())

	if(escolha == 1):
		a = float(input("Escolha o Valor A: "))
		b = float(input("Escolha o Valor B: "))
		R = uniform(0,1)
		x = a+(b-a)*R
		print("")
		print(x)

	elif(escolha == 2):
		a = float(input("Escolha o Valor A: "))
		b = float(input("Escolha o Valor B: "))
		c = float(input("Escolha o Valor C: "))
		R = uniform(0,1)
		var = ((c-a)/(b-a))
		if(R<var):
			x = a + ((R*(c-a)*(b-a))**(1/2))
			print("")
			print(x)
		else:
			x = b - (((1-R)*(b-c)*(b-a))**(1/2))
			print("")
			print(x)

	elif(escolha == 3):
		u = float(input("Escolha o Valor Medio: "))
		R = uniform(0,1)
		x = -(u)*math.log(R)
		print("")
		print(x)

	elif(escolha == 4):
		u = float(input("Escolha o Valor Medio: "))
		o = float(input("Escolha o Valor da Variancia: "))
		R1 = uniform(0,1)
		R2 = uniform(0,1)

		z = ((-2*math.log(R1)) ** (1/2)) * (math.sin(2*math.pi*R2))
		x = (u + (o*z))
		print("")
		print(x)

	elif(escolha == 5):
		print("")
		print("Programa desenvolvido por Luiz Sergio e Lucas Miguel")

	else:
		print("")
		print("Digite um valor do menu")

	print("")
	print("")
	print("")
