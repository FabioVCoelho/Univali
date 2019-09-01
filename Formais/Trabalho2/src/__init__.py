import re
from tkinter import *

matriz = [[1, 10, 4, 8, 10, 10], [10, 2, 10, 10, 10, 10], [3, 10, 10, 10, 10, 10], [10, 0, 10, 10, 10, 10],
          [10, 10, 5, 6, 10, 10], [10, 10, 4, 8, 10, 10], [10, 10, 10, 10, 7, 10], [10, 10, 10, 8, 10, 10],
          [10, 10, 10, 10, 9, 10], [10, 10, 10, 6, 10, 10], [10, 10, 10, 10, 10, 10]]
vetor = [0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0]


def get_column(char_from_sentence):
    if char_from_sentence == "a":
        return 0
    if char_from_sentence == "b":
        return 1
    if char_from_sentence == "c":
        return 2
    if char_from_sentence == "d":
        return 3
    if char_from_sentence == "e":
        return 4
    return 5


def error_type(sentence_with_error):
    matches = re.finditer(r"[^a-e]*", sentence_with_error)
    type_error = False
    for matchNum, match in enumerate(matches, start=1):
        if len(match.group()) > 0:
            type_error = True
    if type_error:
        return "ERRO - Simbolo(s) inválido(s) " + sentence_with_error + "\n"
    else:
        return "ERRO - Sentença inválida " + sentence_with_error + "\n"


def contains_arithmetic_operator(sentence_to_check, result):
    if "+" in sentence_to_check:
        result += "Operador Aritmético + \n"
        return [True, result]
    if "-" in sentence_to_check:
        result += "Operador Aritmético - \n"
        return [True, result]
    if "/" in sentence_to_check:
        result += "Operador Aritmético / \n"
        return [True, result]
    if "*" in sentence_to_check:
        result += "Operador Aritmético * \n"
        return [True, result]
    return [False, result]


def recognizer(entry_caracter):
    caracter = entry_caracter.replace("+", " + ").replace("/", " / ").replace("*", " * ").replace("-", " - ")
    caracter = re.sub(r"\s+", ' ', caracter)
    caracter_vector = caracter.rsplit(" ")
    result = ""
    for sentence in caracter_vector:
        arithmetic, result = contains_arithmetic_operator(sentence, result)
        if not arithmetic:
            estado = 0
            for char in sentence:
                coluna = get_column(char)
                estado = matriz[estado][coluna]
            if vetor[estado] == 1:
                result += "sentença reconhecida " + sentence + "\n"
            else:
                result += error_type(sentence)
    return result


def clean_entry():
    e1.delete(0, 'end')
    my_label.configure(text="")


def show_entry_fields():
    my_label.configure(text="%s" % (recognizer(e1.get())))


if __name__ == '__main__':
    master = Tk()
    Label(master, text="Sequência de caracteres").grid(row=0)

    e1 = Entry(master, width=150)

    e1.grid(row=0, column=1)

    Button(master, text='Quit', command=master.quit).grid(row=1, column=2, sticky=W, pady=4)
    Button(master, text='Show', command=show_entry_fields).grid(row=1, column=1, sticky=W, pady=4)
    clean = Button(master, text='Limpar', command=clean_entry).grid(row=1, column=0)

    res = Label(master, text="Resultado").grid(row=2)
    my_label = Label(master, text="")
    my_label.grid(row=3, column=1)

    master.mainloop()
