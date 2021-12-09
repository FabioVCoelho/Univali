import os

from sklearn import metrics
from sklearn.metrics import confusion_matrix, classification_report, accuracy_score, silhouette_score
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from yellowbrick.cluster import InterclusterDistance

from src_py.DataManipulation import DataManipulation


class KNNUtils:

    def __init__(self):
        self.seed = 212
        self.data_manipulation = DataManipulation()
        self.path = "/home/fabio/Procon/"
        self.extension = ".csv"

    def generate_KNN(self, neighbors):
        model = KNeighborsClassifier(n_neighbors=neighbors)
        return model

    def generate_KNN_with_test(self, dataframe, column_target, columns_data, test_size, neighbors):
        data = dataframe[columns_data].to_numpy()
        target = dataframe[column_target].values.flatten()
        X_train, X_test, y_train, y_test = train_test_split(data, target, test_size=test_size, random_state=self.seed)
        model = self.generate_KNN(neighbors)
        model.fit(X_train, y_train)
        y_pred = model.predict(X_test)
        print("Accuracy:", metrics.accuracy_score(y_test, y_pred))
        return y_test, y_pred

    def run_KNN(self, dataframe, columns_to_analyse, neighbors):
        dataframe_standard = self.load_KNN_dataframe(columns_to_analyse, dataframe)

        return self.generate_KNN_with_test(dataframe_standard, ["Atendida"], columns_to_analyse, 0.3, neighbors)

    def load_KNN_dataframe(self, columns_to_analyse, dataframe):
        columns_to_analyse_string = ''.join(columns_to_analyse)
        if not os.path.exists(self.path + columns_to_analyse_string + self.extension):
            dataframe_standard = self.data_manipulation.generate_dataframe_standard(dataframe, columns_to_analyse)
            dataframe_standard["Atendida"] = dataframe["Atendida"]
            self.data_manipulation.save_dataframe(self.path, columns_to_analyse_string, dataframe_standard)
        else:
            dataframe_standard = self.data_manipulation.load_data(self.path + columns_to_analyse_string + self.extension)
        return dataframe_standard

    def generate_confusion_matriz(self, y_test, y_pred):
        # Imprimindo a matriz confusa
        print("Matriz Confusa: ")
        print(confusion_matrix(y_test, y_pred), "\n")

        # Imprimindo o relatório de classificação
        print("Relatório de classificação: \n", classification_report(y_test, y_pred))

        # Imprimindo o quão acurado foi o modelo
        print('Acurácia do modelo: ', accuracy_score(y_test, y_pred))

    def intra_cluster_statistics(self, dataframe, columns_to_analyse):
        dataframe_standard = self.load_KNN_dataframe(columns_to_analyse, dataframe)
        print(dataframe.shape)
        print(dataframe_standard.shape)
        print(silhouette_score(dataframe_standard, dataframe_standard["Atendida"], random_state=self.seed))
        return "done"

    def inter_cluster_statistics(self, dataframe, columns_to_analyse, clusters):
        from sklearn.neighbors import NearestCentroid
        dataframe_standard = self.load_KNN_dataframe(columns_to_analyse, dataframe)
        model = NearestCentroid()
        visualizer = InterclusterDistance(model)
        visualizer.fit(dataframe_standard)
        visualizer.show()
