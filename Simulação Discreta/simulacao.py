# -*- coding: utf-8 -*-
"""Simulacao.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1aAoNh4uzgCZ8uR0xmF6b14YNmAkhOmy0
"""

import math
import random as rd

import matplotlib.pyplot as plt


def uniform(size, min, max):
    return [min + (max - min) * rd.random() for i in range(size)]


def write_to_file(filename, list_of_values):
    with open(filename, "w") as f:
        for line in list_of_values:
            f.write(str(line).replace(",", "."))
            f.write("\n")


def triangular(size, min, max, moda):
    result = []
    for i in range(size):
        R = rd.random()
        var = (moda - min) / (max - min)
        if R < var:
            x = min + math.sqrt(R * (moda - min) * (max - min))
        else:
            x = max - math.sqrt((1 - R) * (max - moda) * (max - min))
        result.append(x)
    return result


def exponential(size, medium_value):
    return [-medium_value * math.log(rd.random()) for i in range(size)]


def normal(size, medium_value, variance):
    result = []
    for i in range(size):
        R1 = rd.random()
        R2 = rd.random()
        z = ((-2 * math.log(R1)) ** (1 / 2)) * (math.sin(2 * math.pi * R2))
        x = (variance + (medium_value * z))
        result.append(x)
    return result


escolha = 0
while escolha != 5:
    print("Escolhe o tipo de distribuição que deseja usar. ")
    print("1 - Uniforme")
    print("2 - Triangular")
    print("3 - Exponencial")
    print("4 - Normal")
    print("5 - Sair")
    escolha = int(input())
    if escolha == 1:
        a = float(input("Escolha o Valor A: "))
        b = float(input("Escolha o Valor B: "))
        quantidade = int(input("Escolha a quantidade de valores a ser gerado: "))
        uniform_list = uniform(quantidade, a, b)
        write_to_file("uniform.txt", uniform_list)
        fig, axs = plt.subplots(tight_layout=True)
        axs.hist(uniform_list)
        plt.show()

    elif escolha == 2:
        a = float(input("Escolha o Valor A: "))
        b = float(input("Escolha o Valor B: "))
        c = float(input("Escolha o Valor C: "))
        quantidade = int(input("Escolha a quantidade de valores a ser gerado: "))
        triangular_list = triangular(quantidade, a, c, b)
        write_to_file("triangular.txt", triangular_list)
        fig, axs = plt.subplots(tight_layout=True)
        axs.hist(triangular_list)
        plt.show()

    elif escolha == 3:
        u = float(input("Escolha o Valor Medio: "))
        quantidade = int(input("Escolha a quantidade de valores a ser gerado: "))
        exponential_list = exponential(quantidade, u)
        write_to_file("exponential.txt", exponential_list)
        fig, axs = plt.subplots(tight_layout=True)
        axs.hist(exponential_list)
        plt.show()

    elif escolha == 4:
        u = float(input("Escolha o Valor Medio: "))
        o = float(input("Escolha o Valor da Variancia: "))
        quantidade = int(input("Escolha a quantidade de valores a ser gerado: "))
        normal_list = normal(quantidade, u, o)
        write_to_file("normal.txt", normal_list)
        fig, axs = plt.subplots(tight_layout=True)
        axs.hist(normal_list)
        plt.show()

    elif escolha == 5:
        print("")
        print("Programa desenvolvido por Luiz Sergio e Fábio Volkmann Coelho")

    else:
        print("")
        print("Digite um valor do menu")
        print("")
