import sys

sys.path.append('/home/fabio/IdeaProjects/Univali/TCC/')
import os

from src_py.DataManipulation import DataManipulation
from src_py.KMeansUtil import KMeansUtil
from src_py.KNNUtils import KNNUtils


def generate_full_dataframe():
    global dataframe
    if not os.path.exists(path + filename + extension):
        procon_dataframe = data_manipulation.load_all_data()
        data_manipulation.save_dataframe(path, filename, procon_dataframe)
    else:
        procon_dataframe = data_manipulation.load_data(path + filename + extension)
    cep_dataframe = data_manipulation.load_all_cep()
    dataframe = data_manipulation.merge_dataframes(procon_dataframe, cep_dataframe, "CEPConsumidor", "cep")
    return dataframe


def working_with_kmean(dataframe):
    # if not os.path.exists(path + standard_csv + extension):
    # Testado com todos esses atributos, não está sendo formado clusters.
    # columns_to_analyse = ['CodigoAssunto', 'CodigoProblema', 'SexoConsumidor', 'FaixaEtariaConsumidor', 'Atendida', 'RadicalCNPJ',
    #                       'CEPConsumidor']
    # dataframe["SexoConsumidor"] = k_means.convert_to_numeric("SexoConsumidor", dataframe)
    # dataframe["FaixaEtariaConsumidor"] = k_means.convert_to_numeric("FaixaEtariaConsumidor", dataframe)

    # columns_to_analyse = ['CodigoAssunto', 'CodigoProblema', 'Atendida', 'RadicalCNPJ', 'CEPConsumidor']
    # dataframe["SexoConsumidor"] = k_means.convert_to_numeric("SexoConsumidor", dataframe)
    # dataframe["FaixaEtariaConsumidor"] = k_means.convert_to_numeric("FaixaEtariaConsumidor", dataframe)

    columns_to_analyse = ['cidade_bairro', 'Atendida']
    dataframe["Atendida"] = data_manipulation.convert_to_numeric("Atendida", dataframe)
    dataframe["cidade_bairro"] = data_manipulation.convert_to_numeric("cidade_bairro", dataframe)
    dataframe_standard = data_manipulation.transform_to_standard_scale(dataframe, columns_to_analyse)
    dataframe_standard = dataframe_standard.dropna(subset=columns_to_analyse)
    data_manipulation.save_dataframe(path, standard_csv, dataframe_standard)
    dataframe_standard = data_manipulation.load_data(path + standard_csv + extension)

    k_means.generate_KElbow_graph(8, dataframe_standard)
    # model, label = k_means.generate_KMeans(dataframe_standard, 4)
    # k_means.plotThreeD(label, dataframe_standard)


def working_with_knn(dataframe=None):
    columns_to_analyse = ['CodigoAssunto', 'RadicalCNPJ', 'CEPConsumidor']
    y_test, y_pred = knn_utils.run_KNN(dataframe, columns_to_analyse, 5)
    knn_utils.generate_confusion_matriz(y_test, y_pred)


if __name__ == '__main__':
    load_sample = False
    load_from_file = False
    filename = "procon_concated"
    extension = ".csv"
    path = "/home/fabio/Procon/"
    merged_csv = "procon_cep_concated"
    sample_csv = "procon_cep_concated_sample"
    standard_csv = "standard_dataframe"
    data_manipulation = DataManipulation()
    k_means = KMeansUtil()
    knn_utils = KNNUtils()
    if not load_from_file:
        if not os.path.exists(path + merged_csv + extension):
            dataframe = generate_full_dataframe()
            data_manipulation.save_dataframe(path, merged_csv, dataframe)
        elif load_sample:
            if not os.path.exists(path + sample_csv + extension):
                dataframe = generate_full_dataframe()
                data_manipulation.save_sample(path, sample_csv, dataframe)
            dataframe = data_manipulation.load_data(path + sample_csv + extension)
        else:
            dataframe = data_manipulation.load_data(path + merged_csv + extension)
    else:
        dataframe = None

    # data_manipulation.view_df_stats(dataframe)
    # print(len(data_manipulation.unique(dataframe, 'cidade_bairro')))
    # working_with_knn(dataframe=dataframe)
    # working_with_kmean(dataframe)
    # columns_to_analyse = ['CodigoAssunto', 'CodigoProblema', 'cidade_bairro', 'Atendida', 'RadicalCNPJ']
    # k_means.intra_cluster_statistics(dataframe, columns_to_analyse, 5)
    # k_means.inter_cluster_statistics(dataframe, columns_to_analyse, 5)
    # knn_utils.intra_cluster_statistics(dataframe, columns_to_analyse)
    # knn_utils.inter_cluster_statistics(dataframe, columns_to_analyse, 5)
