import React from 'react';
import clsx from 'clsx';
import styles from './HomepageFeatures.module.css';
import useBaseUrl from '@docusaurus/useBaseUrl';

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          <div className={clsx('col col--4')}>
            <div className="text--center">
              <img src={useBaseUrl('/img/scala-icon.png')} className={styles.featureSvg} alt="Credit to https://github.com/OlegIlyenko/scala-icon" />
            </div>
            <div className="text--center padding-horiz--md">
              <h3>Learn Scala 3</h3>
              <p>
                  A simpler, safer and more concise version of Scala,
                  the famous object-oriented and functionnal programming language.
              </p>
            </div>
          </div>
          <div className={clsx('col col--4')}>
            <div className="text--center">
              <img src={useBaseUrl('/img/screenshot.png')} className={styles.featureSvg} />
            </div>
            <div className="text--center padding-horiz--md">
              <h3>Build a Snake Game</h3>
              <p>
                  Explore the concept of programming, hack the code and create your own Snake game.
              </p>
            </div>
          </div>
          <div className={clsx('col col--4')}>
            <div className="text--center">
              <img src={useBaseUrl('/img/community.png')} className={styles.featureSvg} />
            </div>
            <div className="text--center padding-horiz--md">
              <h3>Have fun</h3>
              <p>
                  Meet people and share your passion for coding.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
