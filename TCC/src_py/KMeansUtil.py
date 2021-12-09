import os

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.cluster import KMeans
from yellowbrick.cluster import KElbowVisualizer, InterclusterDistance

from src_py.DataManipulation import DataManipulation


class KMeansUtil:

    def __init__(self):
        self.data_manipulation = DataManipulation()
        self.extension = ".csv"
        self.path = "/home/fabio/Procon/"

        self.cities_numeric = {}
        self.seed = 212

    def generate_elbow_graph(self, dataframe_scaled, how_many_clusters_to_try=range(1, 3)):
        inertias = []

        for k in how_many_clusters_to_try:
            # Create a KMeans instance with k clusters: model
            model = KMeans(n_clusters=k, random_state=self.seed)

            # Fit model to samples
            model.fit(dataframe_scaled.iloc[:, 1:])

            # Append the inertia to the list of inertias
            inertias.append(model.inertia_)

        # Plot ks vs inertias
        plt.plot(how_many_clusters_to_try, inertias, '-o')
        plt.xlabel('number of clusters, k')
        plt.ylabel('inertia')
        plt.xticks(how_many_clusters_to_try)
        plt.show()

    def generate_KElbow_graph(self, clusters, dataframe):
        model = KElbowVisualizer(KMeans(random_state=self.seed), k=clusters)
        model.fit(dataframe)
        model.show()

    def generate_KMeans(self, dataframe, clusters):
        kmeans = KMeans(n_clusters=clusters, random_state=self.seed)
        label = kmeans.fit_predict(dataframe)
        return kmeans, label

    def plotThreeD(self, label, dataframe):
        filtered_label0 = dataframe[label == 0]
        fig = plt.figure()
        ax = fig.add_subplot(projection='3d')
        marks = ["^", "o", "x", "8", "<"]
        for label_value in np.unique(label):
            filtered_label = dataframe[label == label_value]
            xs = filtered_label.iloc[:, 0]
            ys = filtered_label.iloc[:, 2]
            zs = filtered_label.iloc[:, 1]
            ax.scatter(xs, ys, zs, marker=marks[label_value])

        ax.set_xlabel(filtered_label0.columns[0])
        ax.set_ylabel(filtered_label0.columns[2])
        ax.set_zlabel(filtered_label0.columns[1])

        plt.show()

    def plotTwoD(self, label, dataframe):
        filtered_label0 = dataframe[label == 0]
        fig = plt.figure()
        for label_value in np.unique(label):
            filtered_label = dataframe[label == label_value]
            xs = filtered_label.iloc[:, 0]
            ys = filtered_label.iloc[:, 1]
            plt.scatter(xs, ys)
        plt.show()

    def optimalK(self, data, nrefs=3, maxClusters=15):
        """
        Calculates KMeans optimal K using Gap Statistic
        Params:
            data: ndarry of shape (n_samples, n_features)
            nrefs: number of sample reference datasets to create
            maxClusters: Maximum number of clusters to test for
        Returns: (gaps, optimalK)
        """
        gaps = np.zeros((len(range(1, maxClusters)),))
        resultsdf = pd.DataFrame({'clusterCount': [], 'gap': []})
        for gap_index, k in enumerate(range(1, maxClusters)):
            # Holder for reference dispersion results
            refDisps = np.zeros(nrefs)
            # For n references, generate random sample and perform kmeans getting resulting dispersion of each loop
            for i in range(nrefs):
                # Create new random reference set
                randomReference = np.random.random_sample(size=data.shape)

                # Fit to it
                km = KMeans(k)
                km.fit(randomReference)

                refDisp = km.inertia_
                refDisps[i] = refDisp
            # Fit cluster to original data and create dispersion
            km = KMeans(k)
            km.fit(data)

            origDisp = km.inertia_
            # Calculate gap statistic
            gap = np.log(np.mean(refDisps)) - np.log(origDisp)
            # Assign this loop's gap statistic to gaps
            gaps[gap_index] = gap

            resultsdf = resultsdf.append({'clusterCount': k, 'gap': gap}, ignore_index=True)
        return gaps.argmax() + 1, resultsdf

    def generate_standard_dataframe(self, dataframe, columns_to_analyse):
        columns_to_analyse_string = ''.join(columns_to_analyse)
        if not os.path.exists(self.path + columns_to_analyse_string + self.extension):
            dataframe_standard = self.data_manipulation.generate_dataframe_standard(dataframe, columns_to_analyse)
            self.data_manipulation.save_dataframe(self.path, columns_to_analyse_string, dataframe_standard)
        else:
            dataframe_standard = self.data_manipulation.load_data(self.path + columns_to_analyse_string + self.extension)
        return dataframe_standard

    def gap_statistic(self, dataframe, columns_to_analyse):
        dataframe_standard = self.generate_standard_dataframe(dataframe, columns_to_analyse)
        score_g, df = self.optimalK(dataframe_standard, nrefs=5, maxClusters=30)
        plt.plot(df['clusterCount'], df['gap'], linestyle='--', marker='o', color='b')
        plt.xlabel('K')
        plt.ylabel('Gap Statistic')
        plt.title('Gap Statistic vs. K')
        plt.show()

    def intra_cluster_statistics(self, dataframe, columns_to_analyse, clusters):
        dataframe_standard = self.data_manipulation.generate_dataframe_standard(dataframe, columns_to_analyse)
        model = KMeans(n_clusters=clusters, random_state=self.seed)
        visualizer = KElbowVisualizer(model, k=(1, 7), metric='silhouette', timings=True)
        visualizer.fit(dataframe_standard)
        visualizer.show()

    def inter_cluster_statistics(self, dataframe, columns_to_analyse, clusters):
        dataframe_standard = self.data_manipulation.generate_dataframe_standard(dataframe, columns_to_analyse)
        model = KMeans(n_clusters=clusters, random_state=self.seed)
        visualizer = InterclusterDistance(model)
        visualizer.fit(dataframe_standard)
        visualizer.show()
